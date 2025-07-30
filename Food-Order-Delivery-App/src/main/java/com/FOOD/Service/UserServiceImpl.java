package com.FOOD.Service;

import com.FOOD.Models.User;
import com.FOOD.Repo.userRepo;
import com.FOOD.config.jwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    userRepo userRepo;

    @Autowired
    private jwtProvider jwtProvider;


    @Override
    public User findUserByJwtToken(String jwtToken) throws Exception {
        System.out.println("Received JWT token: " + jwtToken);
        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new Exception("JWT Token is null or empty");
        }
        String email = jwtProvider.getEmailFromJwtToken(jwtToken);
        System.out.println("Extracted email from token: " + email);
        if (email == null) {
            throw new Exception("Failed to extract email from token");
        }
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new Exception("No user found with email: " + email);
        }
        return user;
    }



    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new Exception("User not found");
        }

        return user;
    }
}
