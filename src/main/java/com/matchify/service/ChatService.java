package com.matchify.service;

import com.matchify.entity.ChatMessage;
import com.matchify.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository repo;

    public ChatService(ChatRepository repo) {
        this.repo = repo;
    }

    public ChatMessage send(ChatMessage msg) {
        msg.setTime(LocalDateTime.now());
        return repo.save(msg);
    }

    public List<ChatMessage> getChat(Long u1, Long u2) {
        return repo
          .findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimeAsc(
                u1, u2, u2, u1
        );
    }
}