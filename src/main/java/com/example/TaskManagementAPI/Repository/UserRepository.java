package com.example.TaskManagementAPI.Repository;

import com.example.TaskManagementAPI.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


}

