package com.example.BlogCaNhan.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private String roles;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<PostBlog> posts;

    public UserInfo() {

    }

    @JsonCreator
    public UserInfo(@JsonProperty("username") String username, @JsonProperty("email") String email, @JsonProperty("password") String password, @JsonProperty("roles")  String roles, @JsonProperty("posts") List<PostBlog> posts) {
        this.userName = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.posts = posts;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PostBlog> getPosts() {
        return posts;
    }

    public void setPosts(List<PostBlog> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles='" + roles + '\'' +
                ", posts=" + posts +
                '}';
    }
}
