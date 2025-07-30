package com.FOOD.Service;

import com.FOOD.Models.User;

public interface UserService {
    public User findUserByJwtToken(String jwtToken) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
