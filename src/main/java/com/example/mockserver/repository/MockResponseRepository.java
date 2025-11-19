package com.example.mockserver.repository;

import com.example.mockserver.model.MockResponseEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockResponseRepository extends JpaRepository<MockResponseEntity, Long> {
    Optional<MockResponseEntity> findByEndpoint(String endpoint);
}
