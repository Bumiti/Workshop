package com.workshop.dao;

import com.workshop.authentication.OAuthenticationRequest;
import com.workshop.config.MapperGeneric;
import com.workshop.dto.UserEditRequest;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.UserAddresses;
import com.workshop.model.userModel.VerificationToken;
import com.workshop.reposetory.*;
import com.workshop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private final UserAddressRepository userAddressRepository;
    @Override
    public User SaveUser(UserRegisterRequest user) {
        MapperGeneric<User, UserRegisterRequest> mapper = new MapperGeneric<>();
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            throw new RuntimeException("User with email: " + user.getEmail() + "already exists");
        }
        User userMapper = mapper.DTOmapToModel(user, User.class);
        userMapper.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.setEnable(false);
        var result = userRepository.save(userMapper);
        if (result != null) {
            Roles roles = roleRepository.findByName(user.getRole());
            userMapper.getRoles().add(roles);
        }
        return result;
    }
    @Override
    public Boolean EditUser(UserEditRequest user) {
        try {
            Optional<User> userExist = userRepository.findByEmail(user.getEmail());
            if (userExist.isPresent()) {
                User existingUser = userExist.get();
                if (user.getUser_name() != null) {
                    existingUser.setUser_name(user.getUser_name());
                }
                if (user.getFull_name() != null) {
                    existingUser.setFull_name(user.getFull_name());
                }
                if (user.getImage_url() != null) {
                    existingUser.setImage_url(user.getImage_url());
                }
                if (user.getPhoneNumber() != null) {
                    existingUser.setPhoneNumber(user.getPhoneNumber());
                }
                List<UserAddresses> userAddressesList = new ArrayList<>();
                for (UserEditRequest.UserAddress userAddressDTO : user.getUserAddresses()) {
                    UserAddresses userAddresses = new UserAddresses();
                    userAddresses.setAddress(userAddressDTO.getAddress())
                            .setCity(userAddressDTO.getCity())
                            .setState(userAddressDTO.getState())
                            .setPostalCode(userAddressDTO.getPostalCode())
                            .setUser(existingUser);
                    userAddressesList.add(userAddresses);
                }
//                userAddressRepository.saveUserAddresses(userAddressesList);
                userAddressRepository.saveAll(userAddressesList);
                userRepository.save(existingUser);
                return true;
            } else {
                String errorMessage = "User Not Found with Email: " + user.getEmail();
                return false;
            }
        } catch (DataAccessException ex) {
            String errorMessage = "DataAccessException: " + ex.getMessage();
            return false;
        } catch (Exception exception) {
            String errorMessage = "exception: " + exception.getMessage();
            return false;
        }
    }
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
            String Email = (String) authentication.getPrincipal();
            User user = userRepository.findByEmail(Email).get();
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

    @Override
    public String validate(String token) {
        VerificationToken theToken = verificationTokenRepository.findByToken((token));
        if (theToken == null) {
            return "Invalid Verification Token";
        }
        User user = theToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((theToken.getTokenExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenRepository.delete(theToken);
            return "Token Already Expired";
        }
        user.setEnable(true);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public String ResetPasswordByMail(String mail) {
        Optional<User> user = userRepository.findByEmail(mail);
        if (user.isPresent()) {
            User userExit = user.get();
            String newPassword = generateRandomPassword();
            userExit.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(userExit);
            return newPassword;
        } else {
            return null;
        }
    }

    private String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:,.<>?";
        int length = 12;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            password.append(characters.charAt(randomIndex));
        }

        return password.toString();
    }

}
