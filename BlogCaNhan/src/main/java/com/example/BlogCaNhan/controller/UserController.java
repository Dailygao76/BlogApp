package com.example.BlogCaNhan.controller;

import com.example.BlogCaNhan.models.ResponeObject;
import com.example.BlogCaNhan.models.UserInfo;
import com.example.BlogCaNhan.repository.UsersRepository;
import com.example.BlogCaNhan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/Blog/app") //End point gốc
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UserService userService;

    // 1 admin
    @GetMapping("/User/getAllUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    List<UserInfo> getAllUser(){
        return userService.getAllUser();
    }

    // 1 admin
    @PutMapping("/UserUpdate/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponeObject>  updateUser(@RequestBody UserInfo newUser, @PathVariable Long id) {
        return userService.updateUser(newUser,id);
    }

    // 1 admin
    @DeleteMapping("/UserDelete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponeObject>  deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/UserNumBer/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponeObject> findUser(@PathVariable Long id) {
        return userService.findUser(id);
    }




    //    @PostMapping("/user/insert")
//    ResponseEntity<ResponeObject> saveUser(@RequestBody UserInfo newUser){
//        return userService.saveUser(newUser);
//    }


//    @GetMapping("/User/getAllUser")
//    public List<UserInfo> getAllUser() {
//        return usersRepository.findAll();
//    }


//    @PostMapping("/user/insert")
////    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    ResponseEntity<ResponeObject> saveUser(@RequestBody UserInfo newUser) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponeObject("ok","USER is save success", usersRepository.save(newUser))
//        );
//    }


//    @GetMapping("/UserNumBer/{id}")
//    ResponseEntity<ResponeObject> findUser(@PathVariable Long id) {
//        Optional<UserInfo> IdUser = usersRepository.findById(id);
//        if(IdUser.isEmpty()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponeObject("failed", "Cannot find User in database", "")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponeObject("ok","User is find success", IdUser)
//        );
//    }

//    @PutMapping("/UserUpdate/{id}")
//    ResponseEntity<ResponeObject> updateUser(@RequestBody UserInfo newUser, @PathVariable Long id) {
//        UserInfo updatedUser = usersRepository.findById(id)
//                .map(userInfo -> {
//                    userInfo.setUsername(newUser.getUsername());
//                    userInfo.setPassword(newUser.getPassword());
//                    userInfo.setEmail(newUser.getEmail());
//                    userInfo.setRoles(userInfo.getRoles());
//                    return usersRepository.save(userInfo);
//                }).orElseGet(() -> {
//                    newUser.setId(id);
//                    return usersRepository.save(newUser);
//                });
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponeObject("ok", "Update User successfully", updatedUser)
//        );
//    }
}
