package com.example.BlogCaNhan.controller;

import com.example.BlogCaNhan.models.PostBlog;
import com.example.BlogCaNhan.models.ResponeObject;

import com.example.BlogCaNhan.repository.PostRepository;
import com.example.BlogCaNhan.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/Blog/app") //End point gốc
@CrossOrigin(origins = "http://localhost:3000")
//@EnableMethodSecurity
public class PostController {
    @Autowired
    PostRepository postrepository;
    @Autowired
    PostService postService;

    // ca 2
    @GetMapping(path = "/posts")
    List<PostBlog> getAllPosts(){
        return postService.getAllPost();
    }

    // ca 2
    @GetMapping(path = "/PostNumBer/{id}")
    public ResponseEntity<ResponeObject> findPost(@PathVariable Long id) {
        return postService.findPost(id);
    }

    // ca 2
    @PostMapping("/insert")
    public ResponseEntity<ResponeObject> postPost(Principal principal, @RequestBody PostBlog newPostBlog) {
        return postService.createPost(principal.getName(),newPostBlog);
    }

    // ca
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponeObject> deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    @DeleteMapping("admin/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ResponeObject> deletePostByAdmin(@PathVariable Long id) {
        return postService.deletePost(id);
    }

    // ca 2
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponeObject> updatePost(@RequestBody PostBlog newPostBlog, @PathVariable Long id) {
        return postService.updatePost(id, newPostBlog);
    }

}
