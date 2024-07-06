package com.example.BlogCaNhan.models;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name="posts")
public class PostBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition="longtext")
    private String postTitle;

    @Column(nullable = false)
    private String urlSlug;

    @Column(columnDefinition="longtext")
    private String postDescribe;
    @Column(columnDefinition="longtext")
    private String postBody;
    @Column(columnDefinition="longtext")
    private String postImg;

    @Column(columnDefinition="longtext")
    private String nameAuthor;

    @Column(columnDefinition="longtext")
    private String datePost;


    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonBackReference
    private UserInfo user;


    public PostBlog( String postTitle,String urlSlug, String postBody, String postImg,String nameAuthor, String datePost, String postDescribe, UserInfo user) {
        this.postTitle = postTitle;
        this.urlSlug = urlSlug;
        this.postBody = postBody;
        this.postImg = postImg;
        this.nameAuthor = nameAuthor;
        this.datePost = datePost;
        this.postDescribe = postDescribe;
        this.user = user;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public PostBlog(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }


    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getPostDescribe() {
        return postDescribe;
    }

    public void setPostDescribe(String postDescribe) {
        this.postDescribe = postDescribe;
    }

    @Override
    public String toString() {
        return "PostBlog{" +
                "id=" + id +
                ", postTitle='" + postTitle + '\'' +
                ", postDescribe='" + postDescribe + '\'' +
                ", postBody='" + postBody + '\'' +
                ", postImg='" + postImg + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                ", datePost='" + datePost + '\'' +
                ", user=" + user +
                '}';
    }
}
