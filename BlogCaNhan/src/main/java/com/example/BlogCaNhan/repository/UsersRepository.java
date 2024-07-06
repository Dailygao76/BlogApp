package com.example.BlogCaNhan.repository;

import com.example.BlogCaNhan.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserName(String UserName);

}
