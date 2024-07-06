package com.example.BlogCaNhan.config;

import com.example.BlogCaNhan.filter.JwtAuthFilter;
import com.example.BlogCaNhan.repository.UsersRepository;
import com.example.BlogCaNhan.services.UserInfoService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(getPublicRequestMatcher1()).permitAll()
                        .requestMatchers(getPublicRequestMatcher2()).permitAll()
                        .requestMatchers(getPublicRequestMatcher3()).permitAll()
                        .requestMatchers(getPublicRequestMatcher4()).permitAll()
                        .requestMatchers(getAdminRequestMatcher()).authenticated()
                        .requestMatchers(getUsersRequestMatcher()).authenticated()
                )
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private RequestMatcher getPublicRequestMatcher1(){
        return new AntPathRequestMatcher("/auth/generateToken");
    }
    private RequestMatcher getPublicRequestMatcher2(){
        return new AntPathRequestMatcher("/auth/addNewUser");
    }private RequestMatcher getPublicRequestMatcher3(){
        return new AntPathRequestMatcher("/Blog/app/posts");
    }
private RequestMatcher getPublicRequestMatcher4(){
    return new AntPathRequestMatcher("/Blog/app/PostNumBer/{id}");
}






    private RequestMatcher getAdminRequestMatcher() {
        return new AntPathRequestMatcher("/auth/Profile/me");
        // Add setServletPath("/yourServletPath") here if needed
    }
    private RequestMatcher getUsersRequestMatcher() {
        return new AntPathRequestMatcher("/Blog/app/**");
        // Add setServletPath("/yourServletPath") here if needed
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService(usersRepository);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return (AuthenticationProvider) authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
