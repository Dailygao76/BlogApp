package com.example.BlogCaNhan.services;

import com.example.BlogCaNhan.models.PostBlog;
import com.example.BlogCaNhan.models.ResponeObject;
import com.example.BlogCaNhan.models.UserInfo;
import com.example.BlogCaNhan.repository.PostRepository;
import com.example.BlogCaNhan.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    UserService userService;
    @Autowired
    UsersRepository usersRepository;

    //Lấy hết
    public List<PostBlog> getAllPost(){
        return postRepository.findAll();
    }


    //Lấy 1 Post
    public ResponseEntity<ResponeObject> findPost(Long id) {
        Optional<PostBlog> IdPost = postRepository.findById(id);
        if (IdPost.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponeObject("failed", "Cannot find Post in database", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "Post is find success", IdPost)
        );
    }



    //Thêm 1 cái
//    public ResponseEntity<ResponeObject> createPost(@RequestBody PostBlog newPostBlog) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponeObject("ok","Post is success", postRepository.save(newPostBlog))
//        );
//    }




    public static String convertToSlug(String text) {
        String normalized = text.toLowerCase();


        String temp = Normalizer.normalize(normalized, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        normalized = pattern.matcher(temp).replaceAll("");

        normalized = normalized.replaceAll("\\s+", "-").replaceAll("[^a-z0-9-]", "");
        return normalized;
    }

    //Thêm 1 cái
    public ResponseEntity<ResponeObject> createPost(String userName,@RequestBody PostBlog newPostBlog) {
        UserInfo userInfo = usersRepository.findByUserName(userName).orElseGet(null);
        if(userInfo != null){ //Nếu không Null
            //Set tên tác giả với id của tác giả
            newPostBlog.setNameAuthor(userInfo.getUsername());
            newPostBlog.setUser(userInfo);
            newPostBlog.setUrlSlug(convertToSlug(newPostBlog.getPostTitle()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok","Post is success", postRepository.save(newPostBlog))
        );
    }

    //xóa 1 cái
    public ResponseEntity<ResponeObject> deletePost(Long id) {
        boolean exists = postRepository.existsById(id);
        if (exists) {
            postRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject("ok", "Delete Post successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponeObject("failed", "Cannot find Post to delete", "")
        );
    }

    //Sua 1 cai
    public ResponseEntity<ResponeObject> updatePost(Long id, PostBlog newPostBlog) {
        PostBlog updatedPost = postRepository.findById(id)
                .map(postBlog -> {
                    postBlog.setPostTitle(newPostBlog.getPostTitle());
                    postBlog.setPostBody(newPostBlog.getPostBody());
                    postBlog.setPostImg(newPostBlog.getPostImg());
                    return postRepository.save(postBlog);
                }).orElseGet(() -> {
                    newPostBlog.setId(id);
                    return postRepository.save(newPostBlog);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "Update Post successfully", updatedPost)
        );
    }
}
