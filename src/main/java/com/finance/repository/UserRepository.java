package com.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
}