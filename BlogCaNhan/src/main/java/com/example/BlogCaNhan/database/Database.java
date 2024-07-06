//package com.example.BlogCaNhan.database;
//
//import com.example.BlogCaNhan.repository.PostRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class Database {
//    // Logger
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Bean
//    CommandLineRunner initDatabase() {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
////                // Tạo một UserAdmin và lưu vào cơ sở dữ liệu
////                UserAdmin admin = new UserAdmin("Dat dep trai");
////                userAdminRepository.save(admin);
////
////                // Tạo các PostBlog và gán UserAdmin đã có id cho chúng
////                PostBlog blogA = new PostBlog("Iphone is make by titan ?", "Iphone duoc lam hoan toan tu titan", "", admin);
////                PostBlog blogB = new PostBlog("figure expensive in the world", "Iphone duoc lam hoan toan tu titan", "", admin);
////
////                // Lưu các PostBlog vào cơ sở dữ liệu
////                postRepository.save(blogA);
////                postRepository.save(blogB);
////
////                logger.info("Data insertion complete.");
//            }
//        };
//    }
//}

