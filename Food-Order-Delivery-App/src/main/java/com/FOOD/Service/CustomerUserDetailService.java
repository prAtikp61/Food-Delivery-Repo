package com.FOOD.Service;

import com.FOOD.Models.USER_ROLE;
import com.FOOD.Models.User;
import com.FOOD.Repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private userRepo userRepo;

    @Override
    public  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user = userRepo.findByEmail(username);

       if(user == null) {
           throw new UsernameNotFoundException("User not found");
       }
        USER_ROLE userRole = user.getRole();

       List<GrantedAuthority> authorities = new ArrayList<>();

       authorities.add(new SimpleGrantedAuthority(userRole.toString()));

       return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities) ;
    }
}
