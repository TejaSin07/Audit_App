package com.tys.AuditPro.security;

import com.tys.AuditPro.domain.user.Role;
import com.tys.AuditPro.domain.user.User;
import com.tys.AuditPro.repository.RoleRepository;
import com.tys.AuditPro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Role managerRole = roleRepository.findByName("MANAGER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("MANAGER").build()));

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> roleRepository.save(
                        Role.builder().name("USER").build()));

        userRepository.findByUsername("manager")
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .username("manager")
                                .password(passwordEncoder.encode("manager123"))
                                .role(managerRole)
                                .active(true)
                                .build()
                ));

        userRepository.findByUsername("user")
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .username("user")
                                .password(passwordEncoder.encode("user123"))
                                .role(userRole)
                                .active(true)
                                .build()
                ));
    }
}
