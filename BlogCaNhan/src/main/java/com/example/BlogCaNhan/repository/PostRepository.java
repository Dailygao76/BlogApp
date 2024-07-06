package com.example.BlogCaNhan.repository;

import com.example.BlogCaNhan.models.PostBlog;
import com.example.BlogCaNhan.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<PostBlog, Long> {
    PostBlog findByUser(UserInfo user);
    List<PostBlog> findByPostTitle(String postTitle);

}
