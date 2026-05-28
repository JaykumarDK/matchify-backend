package com.matchify.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.matchify.entity.ChatMessage;
import com.matchify.repository.ChatRepository;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    @Autowired
    private ChatRepository repo;

    // ---------- SEND MESSAGE ----------
    @PostMapping("/send")
    public ChatMessage send(@RequestBody ChatMessage msg) {

        msg.setTime(LocalDateTime.now());
        return repo.save(msg);
    }

    // ---------- LOAD CHAT ----------
    @GetMapping("/{user1}/{user2}")
    public List<ChatMessage> getChat(
            @PathVariable Long user1,
            @PathVariable Long user2) {

        return repo.findChat(user1, user2);
    }

    // ---------- DELETE SINGLE MESSAGE ----------
    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        repo.deleteById(id);
    }

    // ---------- DELETE ENTIRE CHAT ----------
    @DeleteMapping("/delete/{u1}/{u2}")
    public void deleteChat(
            @PathVariable Long u1,
            @PathVariable Long u2) {

        repo.deleteChatBetweenUsers(u1, u2);
    }
}