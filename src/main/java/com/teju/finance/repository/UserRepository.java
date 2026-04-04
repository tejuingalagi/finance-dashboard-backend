package com.teju.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teju.finance.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}