package com.matchify.repository;

import com.matchify.entity.ChatMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
        SELECT c FROM ChatMessage c
        WHERE (c.senderId = :u1 AND c.receiverId = :u2)
           OR (c.senderId = :u2 AND c.receiverId = :u1)
        ORDER BY c.time ASC
    """)
    List<ChatMessage> findChat(
            @Param("u1") Long u1,
            @Param("u2") Long u2
    );

    @Modifying
    @Transactional
    @Query("""
        DELETE FROM ChatMessage c
        WHERE (c.senderId = :u1 AND c.receiverId = :u2)
           OR (c.senderId = :u2 AND c.receiverId = :u1)
    """)
    void deleteChatBetweenUsers(
            @Param("u1") Long u1,
            @Param("u2") Long u2
    );

	List<ChatMessage> findBySenderIdAndReceiverIdOrSenderIdAndReceiverIdOrderByTimeAsc(Long u1, Long u2, Long u22,
			Long u12);
}