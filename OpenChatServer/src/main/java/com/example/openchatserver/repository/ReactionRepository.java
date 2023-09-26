package com.example.openchatserver.repository;

import com.example.openchatserver.entity.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction,Long> {
}
