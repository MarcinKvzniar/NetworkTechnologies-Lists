package com.project.networktechproject.infrastructure.repository;

import com.project.networktechproject.infrastructure.entity.BookDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDetailRepository extends JpaRepository<BookDetailEntity, Long> { }
