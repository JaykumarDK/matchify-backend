package com.matchify.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.matchify.dto.MatchResponse;
import com.matchify.service.MatchService;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // ---------- BASIC MATCHES ----------
    @GetMapping("/{userId}")
    public List<MatchResponse> getMatches(@PathVariable int userId) {
        return matchService.getMatches(userId);
    }
    @GetMapping("/{userId}/filter")
    public List<MatchResponse> getFilteredMatches(
            @PathVariable int userId,

            @RequestParam(required = false) String caste,
            @RequestParam(required = false) String education,
            @RequestParam(required = false) String jobLocation,

            @RequestParam(defaultValue = "18") int minAge,
            @RequestParam(defaultValue = "60") int maxAge,

            @RequestParam(defaultValue = "0") int minIncome,

            @RequestParam(defaultValue = "140") int minHeight,
            @RequestParam(defaultValue = "210") int maxHeight,

            @RequestParam(defaultValue = "score") String sortBy) {

        return matchService.getFilteredMatches(
                userId,
                caste,
                education,
                jobLocation,
                minAge,
                maxAge,
                minIncome,
                minHeight,
                maxHeight,
                sortBy
        );
    }
}
