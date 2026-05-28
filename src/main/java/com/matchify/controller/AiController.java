package com.matchify.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/ai")
public class AiController {

    @Value("${anthropic.api.key:NOT_SET}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    // ── Health check — call GET /api/ai/health to verify controller is reachable ──
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        boolean keySet = apiKey != null && !apiKey.equals("NOT_SET")
                && !apiKey.equals("YOUR_ANTHROPIC_API_KEY_HERE")
                && apiKey.startsWith("sk-");
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "keyConfigured", keySet,
                "keyPrefix", apiKey.length() > 10 ? apiKey.substring(0, 10) + "..." : "(too short)"
        ));
    }

    // ── Main AI proxy ──
    @PostMapping("/ask")
    public ResponseEntity<?> ask(@RequestBody Map<String, String> body) {

        String prompt = body.get("prompt");

        if (prompt == null || prompt.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Prompt is required"));
        }

        // Guard: API key not configured
        if (apiKey == null || apiKey.equals("NOT_SET")
                || apiKey.equals("YOUR_ANTHROPIC_API_KEY_HERE")) {
            return ResponseEntity.status(500)
                    .body(Map.of("error", "Anthropic API key not configured in application.properties"));
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-api-key", apiKey);
            headers.set("anthropic-version", "2023-06-01");

            Map<String, Object> message = Map.of(
                    "role", "user",
                    "content", prompt
            );

            Map<String, Object> requestPayload = Map.of(
                    "model", "claude-sonnet-4-20250514",
                    "max_tokens", 300,
                    "messages", List.of(message)
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestPayload, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    "https://api.anthropic.com/v1/messages",
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            Map<?, ?> responseBody = response.getBody();
            String text = "";

            if (responseBody != null) {
                List<?> content = (List<?>) responseBody.get("content");
                if (content != null && !content.isEmpty()) {
                    Map<?, ?> firstBlock = (Map<?, ?>) content.get(0);
                    Object rawText = firstBlock.get("text");
                    if (rawText != null) text = rawText.toString();
                }
            }

            return ResponseEntity.ok(Map.of("text", text));

        } catch (HttpClientErrorException e) {
            // Anthropic returned 4xx — show exact error (auth failure, bad request, etc.)
            System.err.println("Anthropic API error " + e.getStatusCode() + ": " + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of(
                            "error", "Anthropic API error: " + e.getStatusCode(),
                            "detail", e.getResponseBodyAsString()
                    ));

        } catch (Exception e) {
            // Print full stack trace to Spring Boot console
            e.printStackTrace();
            return ResponseEntity.status(500)
                    .body(Map.of(
                            "error", e.getClass().getSimpleName() + ": " + e.getMessage()
                    ));
        }
    }
}
