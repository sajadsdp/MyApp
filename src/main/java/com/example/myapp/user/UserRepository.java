package com.example.myapp.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface UserRepository extends JpaRepository<User,Integer> {
}
