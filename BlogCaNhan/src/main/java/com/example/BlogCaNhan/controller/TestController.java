package com.example.BlogCaNhan.controller;


import com.example.BlogCaNhan.models.AuthRequest;
import com.example.BlogCaNhan.models.PostBlog;
import com.example.BlogCaNhan.models.ResponeObject;
import com.example.BlogCaNhan.models.UserInfo;
import com.example.BlogCaNhan.repository.UsersRepository;
import com.example.BlogCaNhan.services.JwtService;
import com.example.BlogCaNhan.services.PostService;
import com.example.BlogCaNhan.services.UserInfoService;
import com.example.BlogCaNhan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {


    @Autowired
    private UserInfoService service;

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    //Ca 2
    @PostMapping("/addNewUser")
    public UserInfo addNewUser(@RequestBody UserInfo userInfo) {
        return service.addUser(userInfo);
    }

    //Ca 2
    @GetMapping("/Profile/me")
    public ResponseEntity<Optional<UserInfo>> Profile(Principal principal) {
        String username = principal.getName();
        Optional<UserInfo> user = usersRepository.findByUserName(username);
        return ResponseEntity.ok(user);
    }


    //Ca 2
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }




//    @GetMapping("/admin/adminProfile")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<Optional<UserInfo>> adminProfile(Principal principal) {
//        String username = principal.getName();
//        Optional<UserInfo> user = usersRepository.findByUserName(username);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/user/userProfile")
//    public ResponseEntity<Optional<UserInfo>>userProfile(Principal principal) {
//        String username = principal.getName();
//        Optional<UserInfo> user = usersRepository.findByUserName(username);
//        return ResponseEntity.ok(user);
//    }
}
