package com.chronosx.bikeshop.configuration;

import java.util.HashSet;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chronosx.bikeshop.entity.User;
import com.chronosx.bikeshop.repository.RoleRepository;
import com.chronosx.bikeshop.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class InitConfig {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            if (!userRepository.existsUserByUsername("admin")) {
                var role = new HashSet<>(roleRepository.findAll());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .email("admin")
                        .fullName("admin")
                        .isActive(true)
                        .roles(role)
                        .build();
                userRepository.save(user);
                log.info("admin created successfully");
            } else {
                log.warn("already have admin");
            }
        };
    }
}
