package com.example.BlogCaNhan.config;

import com.example.BlogCaNhan.models.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoUserDetails implements UserDetails {
    private Long id; // Thêm trường ID
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(UserInfo userInfo) {
        name = userInfo.getUsername();
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Trả về danh sách quyền của người dùng.
    }

    @Override
    public String getPassword() {
        return password; // Trả về mật khẩu của người dùng.
    }

    @Override
    public String getUsername() {
        return name; // Trả về tên người dùng.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Trả về true nếu tài khoản không hết hạn.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Trả về true nếu tài khoản không bị khóa.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Trả về true nếu thông tin đăng nhập của tài khoản không hết hạn.
    }

    @Override
    public boolean isEnabled() {
        return true; // Trả về true nếu tài khoản hoạt động (không bị vô hiệu hóa).
    }
}
