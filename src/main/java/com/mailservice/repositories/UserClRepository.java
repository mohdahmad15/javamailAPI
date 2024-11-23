package com.mailservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mailservice.entities.UserEntityCl;

public interface UserClRepository extends JpaRepository<UserEntityCl, Long> {
    boolean existsByEmail(String email);
}
