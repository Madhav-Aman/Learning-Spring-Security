package com.security.springboot.SpringSecurityBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.security.springboot.SpringSecurityBoot.security.ApplicationUserRole.ADMIN;
import static com.security.springboot.SpringSecurityBoot.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
 public class ApplicationSecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{

        http.authorizeHttpRequests((authorize)->{
            authorize.
                    requestMatchers("/","index","/css/*","/js/*").permitAll()
                    .requestMatchers("/api/**").hasRole(STUDENT.name())
                    .anyRequest()
                    .authenticated();
        }).httpBasic(Customizer
                .withDefaults());
        return http.build();
    }


    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails madhav = User.builder()
                .username("madhavaman")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name())
                .build();

        UserDetails linda = User.builder()
                .username("lindaketh")
                .password(passwordEncoder.encode("password123"))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                madhav,
                linda
        );
    }


}
