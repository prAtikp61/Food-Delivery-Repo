package com.FOOD.Controllers;

import com.FOOD.Dto_data_transfer_object.RegisterRequest;
import com.FOOD.Models.Cart;
import com.FOOD.Models.USER_ROLE;
import com.FOOD.Models.User;
import com.FOOD.Repo.CartRepo;
import com.FOOD.Repo.userRepo;
import com.FOOD.Request.LoginRequest;
import com.FOOD.Response.AuthResponse;
import com.FOOD.Service.CustomerUserDetailService;
import com.FOOD.config.jwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web
        .bind.annotation.RestController;

import java.util.Collection;

@RequestMapping("/auth")
@RestController
public class AuthController {

    @Autowired
    private userRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private jwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CartRepo cartRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody RegisterRequest request) throws Exception {
        User isEmailExist = userRepo.findByEmail(request.getEmail());
        System.out.println("Incoming fullName: " + request.getFullName());
        if (isEmailExist != null) {
            throw new Exception("user already exists");
        }

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());
        newUser.setRole(request.getRole());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepo.save(newUser);
        System.out.println("Saved user fullName: " + savedUser.getFullName());

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser_role(savedUser.getRole());
        System.out.println("Full name received: " + newUser.getFullName());


        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }


    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>signin(@RequestBody LoginRequest req){
       String username=req.getEmail();
       String password=req.getPassword();

       Authentication authentication=authenticate(username,password);

       Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
       String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

       String jwt =  jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Logged in Successfully");
        authResponse.setUser_role(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customerUserDetailService.loadUserByUsername(username);
        if(userDetails==null){
            throw new UsernameNotFoundException("username not found");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Bad credentials");
        }
        return new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
    }


}
