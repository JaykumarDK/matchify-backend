package com.matchify.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;
    private Long receiverId;

    @Column(length = 1000)
    private String message;

    private LocalDateTime time;

    public Long getId() { return id; }
    public Long getSenderId() { return senderId; }
    public Long getReceiverId() { return receiverId; }
    public String getMessage() { return message; }
    public LocalDateTime getTime() { return time; }

    public void setSenderId(Long senderId) { this.senderId = senderId; }
    public void setReceiverId(Long receiverId) { this.receiverId = receiverId; }
    public void setMessage(String message) { this.message = message; }
    public void setTime(LocalDateTime time) { this.time = time; }
}