package com.project.networktechproject.infrastructure.repository;

import com.project.networktechproject.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> { }
