package com.workshop.dao;

import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.*;
import com.workshop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public User SaveUser(UserRegisterRequest user) {
        User users = new User();
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        users.setEmail(user.getEmail());
        users.setFull_name(user.getFull_name());
        users.setUser_name(user.getUser_name());

        return userRepository.save(users);
    }

    @Override
    public Roles SaveRoles(Roles role) {
        return roleRepository.save(role);
    }
    @Override
    public Void AddRoleToUser(String user_name, String role_name) {
        User user = userRepository.findByEmail(user_name).get();
        Roles roles = roleRepository.findByName(role_name);
        user.getRoles().add(roles);
        return null;
    }

    @Override
    public User getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String) {
            String email = (String) authentication.getPrincipal();
            User user = userRepository.findByEmail(email).get();
            return user;
        }
        return null;
    }

}
