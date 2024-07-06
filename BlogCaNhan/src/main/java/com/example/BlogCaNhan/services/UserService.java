package com.example.BlogCaNhan.services;

import com.example.BlogCaNhan.models.ResponeObject;
import com.example.BlogCaNhan.models.UserInfo;
import com.example.BlogCaNhan.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public record  UserService (UsersRepository usersRepository,
                           PasswordEncoder passwordEncoder) {

    //Lay tat ca
    public List<UserInfo> getAllUser() {
        return usersRepository.findAll();
    }


    //Them 1 user
    public ResponseEntity<ResponeObject> saveUser(@RequestBody UserInfo newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok","USER is save success", usersRepository.save(newUser))
        );
    }


    //Lay 1 user
    public ResponseEntity<ResponeObject> findUser(Long id) {
        Optional<UserInfo> IdUser = usersRepository.findById(id);
        if (IdUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponeObject("failed", "Cannot find User in database", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "User is find success", IdUser)
        );
    }


    //Sua 1 thang
    public ResponseEntity<ResponeObject> updateUser(@RequestBody UserInfo newUser,Long id) {
        UserInfo updatedUser = usersRepository.findById(id)
                .map(userInfo -> {
                    userInfo.setUsername(newUser.getUsername());
                    userInfo.setPassword(newUser.getPassword());
                    userInfo.setEmail(newUser.getEmail());
                    userInfo.setRoles(newUser.getRoles());
                    return usersRepository.save(userInfo);
                }).orElseGet(() -> {
                    newUser.setId(id);
                    return usersRepository.save(newUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "Update User successfully", updatedUser)
        );
    }


    //xóa 1 thang
    public ResponseEntity<ResponeObject> deleteUser(Long id) {
        boolean exists = usersRepository.existsById(id);
        if (exists) {
            usersRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("ok", "Delete User successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("failed", "Cannot find User to delete", "")
        );
    }
}

