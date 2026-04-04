package com.project.SyncRide.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private DatabaseLoginFailureHandler databaseLoginFailureHandler;
     
    @Autowired
    private DatabaseLoginSuccessHandler databaseLoginSuccessHandler;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests((auth) -> auth
                .requestMatchers("/css/**", "/img/**", "/js/**").permitAll()
                .requestMatchers("/", "/login", "/register", "/confirm", "/setup", "/privacy-policy", "/terms-and-conditions", "/error").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((formLogin) -> formLogin
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .failureHandler(databaseLoginFailureHandler)
                .successHandler(databaseLoginSuccessHandler)
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?successLogout")
                .invalidateHttpSession(true)
                .permitAll());
        return http.build();
    }
}
