package com.FOOD.Controllers;

import com.FOOD.Models.User;
import com.FOOD.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class userController {
    @Autowired
private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findUserByJwtToken(@RequestHeader(value = "Authorization", required = false) String token) throws Exception {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new Exception("Authorization header is missing or malformed");
        }
        String jwt = token.substring(7); // remove "Bearer "
        User user = userService.findUserByJwtToken(jwt);
        System.out.println("Returned User fullName = " + user.getFullName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
