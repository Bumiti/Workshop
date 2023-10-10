package com.workshop.dao;

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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
        Optional<User> userexist  = userRepository.findByEmail(user.getEmail());
        if(userexist.isPresent()){
            throw new RuntimeException("User with email: " + user.getEmail() + "already exists");
        }
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
        User usera = mapper.DTOmapToModel(user, User.class);
        usera.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User result = userRepository.save(usera);
            if (result != null) {
                Roles roles = roleRepository.findByName("SELLER");
                usera.getRoles().add(roles);
            }
            return result;
        } catch (DataAccessException e) {
            // Handle database-related exceptions here, e.g., DataIntegrityViolationException
            // Log the error or throw a custom exception if needed
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
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
            // Lưu lại thông tin người dùng sau khi thêm vai trò
            userRepository.save(user);
            // Hoặc bạn có thể sử dụng userRepository.flush() để lưu ngay lập tức mà không cần đợi cho việc lưu tự động
        } else {
            // Xử lý khi không tìm thấy người dùng với user_name cụ thể
            throw new RuntimeException("Không tìm thấy người dùng với user_name: " + user_name);
            // Bạn cần định nghĩa UserNotFoundException hoặc sử dụng một ngoại lệ phù hợp với ứng dụng của bạn
        }
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

    @Override
    public void saveUserVerificationToken(User user, String verificationToken) {
        var verification_token = new VerificationToken(verificationToken,user);
        verificationTokenRepository.save(verification_token);
    }

}
