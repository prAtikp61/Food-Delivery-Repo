package com.FOOD.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeContrller {
@GetMapping
    public ResponseEntity<String> hc(){
        return new ResponseEntity<>("its home page",HttpStatus.OK );
    }

}
