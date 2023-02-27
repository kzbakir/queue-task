package com.example.queuetask.repository;

import com.example.queuetask.model.entity.GeneratedCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeneratedCodeRepository extends JpaRepository<GeneratedCode, Long> {
    GeneratedCode findFirstByLastCodeNotNull();
}
