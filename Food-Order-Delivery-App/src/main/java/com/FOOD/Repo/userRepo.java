package com.FOOD.Repo;

import com.FOOD.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface userRepo extends JpaRepository<User,Long> {
    public User findByEmail(String username);
}
