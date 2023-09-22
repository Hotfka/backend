package com.example.openchatserver.repository;

import com.example.openchatserver.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
