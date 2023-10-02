package com.workshop.dao;

import com.workshop.config.MapperGeneric;
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
        MapperGeneric<User, UserRegisterRequest> mapper = new MapperGeneric<>();
        User usera =mapper.DTOmapToModel(user,User.class);
        usera.setPassword(passwordEncoder.encode(user.getPassword()));
        var result = userRepository.save(usera);
        if(result!=null){
            Roles roles = roleRepository.findByName("USER");
            usera.getRoles().add(roles);
        }
        return result;
    }

    @Override
    public User SaveSeller(UserRegisterRequest user) {
        MapperGeneric<User, UserRegisterRequest> mapper = new MapperGeneric<>();
        User usera =mapper.DTOmapToModel(user,User.class);
        usera.setPassword(passwordEncoder.encode(user.getPassword()));
        var result = userRepository.save(usera);
        if(result!=null){
            Roles roles = roleRepository.findByName("SELLER");
            usera.getRoles().add(roles);
        }
        return result;
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
