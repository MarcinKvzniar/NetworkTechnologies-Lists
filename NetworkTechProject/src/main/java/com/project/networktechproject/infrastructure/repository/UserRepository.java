package com.project.networktechproject.infrastructure.repository;

import com.project.networktechproject.infrastructure.entity.AuthEntity;
import com.project.networktechproject.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}

