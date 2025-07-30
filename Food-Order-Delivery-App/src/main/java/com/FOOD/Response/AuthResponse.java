package com.FOOD.Response;

import com.FOOD.Models.USER_ROLE;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;


public class AuthResponse {
    public String jwt;
    public String message;
    public USER_ROLE user_role;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public USER_ROLE getUser_role() {
        return user_role;
    }

    public void setUser_role(USER_ROLE user_role) {
        this.user_role = user_role;
    }
}
