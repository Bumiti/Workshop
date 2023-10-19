package com.workshop.dao;

import com.workshop.authentication.OAuthenticationRequest;
import com.workshop.config.MapperGeneric;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.VerificationToken;
import com.workshop.reposetory.*;
import com.workshop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User SaveUser(UserRegisterRequest user) {
        MapperGeneric<User, UserRegisterRequest> mapper = new MapperGeneric<>();
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new RuntimeException("User with email: " + user.getEmail() + "already exists");
        }
        User userMapper = mapper.DTOmapToModel(user, User.class);
        userMapper.setPassword(passwordEncoder.encode(user.getPassword()));
        var result = userRepository.save(userMapper);
        if (result != null) {
            Roles roles = roleRepository.findByName(user.getRole());
            userMapper.getRoles().add(roles);
        }
        return result;
    }
//    @Override
//    public User SaveSeller(UserRegisterRequest user) {
//        MapperGeneric<User, UserRegisterRequest> mapper = new MapperGeneric<>();
//        User usera = mapper.DTOmapToModel(user, User.class);
//        usera.setPassword(passwordEncoder.encode(user.getPassword()));
//        try {
//            User result = userRepository.save(usera);
//            if (result != null) {
//                Roles roles = roleRepository.findByName("SELLER");
//                usera.getRoles().add(roles);
//            }
//            return result;
//        } catch (DataAccessException e) {
//            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
//        }
//    }
    @Override
    public User SaveUserOAuthen(OAuthenticationRequest OAuthen) {
        Optional<User> userexist = userRepository.findByEmail(OAuthen.getEmail());
        if (userexist.isPresent()) {
            return userexist.get();
        } else {
            User use = new User();
            use.setEmail(OAuthen.getEmail()).setUser_name(OAuthen.getEmail()).setFull_name(OAuthen.getEmail())
                    .setEnable(true)
                    .setPassword(passwordEncoder.encode(OAuthen.getEmail()));
            var result = userRepository.save(use);
            if (result != null) {
                Roles roles = roleRepository.findByName("USER");
                use.getRoles().add(roles);
            }
            return result;
        }
    }


    @Override
    public Roles SaveRoles(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public Void AddRoleToUser(String user_name, String role_name) {
        Optional<User> userOptional = userRepository.findByEmail(user_name);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Roles roles = roleRepository.findByName(role_name);
            user.getRoles().add(roles);
            userRepository.save(user);

        } else {
            throw new RuntimeException("Không tìm thấy người dùng với user_name: " + user_name);
        }
        return null;
    }

    //Lấy thông tin user từ token
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

    //Lưu token xác thực user
    @Override
    public void saveUserVerificationToken(User user, String verificationToken) {
        var verification_token = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(verification_token);
    }

    //Xử lý token xác thực user qua mail
    @Override
    public String validate(String token) {
        VerificationToken thetoken = verificationTokenRepository.findByToken((token));
        if (thetoken == null) {
            return "Invalid Verification Token";
        }
        User user = thetoken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((thetoken.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(thetoken);
            return "Token Already Expired";
        }
        user.setEnable(true);
        userRepository.save(user);
        return "valid";
    }

}
