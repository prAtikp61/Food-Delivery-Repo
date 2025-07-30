package com.FOOD.Repo;

import com.FOOD.Models.Addresses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface addressRepo extends JpaRepository<Addresses,Long> {
}
