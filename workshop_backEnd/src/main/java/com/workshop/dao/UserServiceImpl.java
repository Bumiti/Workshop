package com.workshop.dao;

import com.workshop.authentication.OAuthenticationRequest;
import com.workshop.config.MapperGeneric;
import com.workshop.dto.useDTO.*;
import com.workshop.model.userModel.*;
import com.workshop.reposetory.*;
import com.workshop.reposetory.User.*;
import com.workshop.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.security.SecureRandom;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAddressRepository userAddressRepository;

    @Override
    @Transactional
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
        Roles roles = roleRepository.findByName(user.getRole());
        userMapper.getRoles().add(roles);
        return result;
    }
    @Override
    @Transactional
    public Boolean EditUser(UserEditRequest user) {
        MapperGeneric<UserAddresses, UserEditRequest.UserAddress> UserAddressable = new MapperGeneric<>();
        List<UserEditRequest.UserAddress> userAddressList = user.getUserAddresses();
        try {
            User existingUser = userRepository.getUserEditByMail(user.getEmail());
            if (existingUser!=null) {
                existingUser.setUser_name(user.getUser_name() != null ? user.getUser_name() : existingUser.getUser_name());
                existingUser.setFull_name(user.getFull_name() != null ? user.getFull_name() : existingUser.getFull_name());
                existingUser.setImage_url(user.getImage_url() != null ? user.getImage_url() : existingUser.getImage_url());
                existingUser.setPhoneNumber(user.getPhoneNumber() != null ? user.getPhoneNumber() : existingUser.getPhoneNumber());
                userRepository.updateUser(existingUser);
                if(userAddressList!=null && (long) userAddressList.size() >0)
                {
                    for(UserEditRequest.UserAddress userAddressItem  :userAddressList)
                    {
                        UserAddresses userAddresses = UserAddressable.DTOmapToModel(userAddressItem,UserAddresses.class);
                        Long useAddress_id = userAddressItem.getId();
                        if(useAddress_id >0){
                            userAddressRepository.updateUserAddressById(useAddress_id,userAddresses);
                        }else{
                            userAddresses.setUser(existingUser);
                            userAddressRepository.save(userAddresses);
                        }
                    }
                }
                return true;
        }else{return false;}
        } catch (Exception exception) {
            return false;
        }
    }
    @Override
    @Transactional
    public User SaveUserOAuthed(OAuthenticationRequest OAuth) {
        Optional<User> userExist = userRepository.findByEmail(OAuth.getEmail());
        if (userExist.isPresent()) {
            return userExist.get();
        } else {
            User use = new User();
            use.setEmail(OAuth.getEmail()).setUser_name(OAuth.getEmail()).setFull_name(OAuth.getEmail())
            .setEnable(true)
            .setPassword(passwordEncoder.encode(OAuth.getEmail()));
            var result = userRepository.save(use);
            Roles roles = roleRepository.findByName("USER");
            use.getRoles().add(roles);
            return result;
        }
    }
    @Override
    public void SaveRoles(Roles role) {
        roleRepository.save(role);
    }
    @Override
    @Transactional
    public boolean DeleteAddress(Long useAddress_id) {
        User existingUser = getCurrentUserDetails();
        if(existingUser!=null){
            int result = userAddressRepository.deleteUserAddressesByUserAndId(existingUser,useAddress_id);
            return result > 0;
        }else{
            return false;
        }
    }
    @Override
    public User getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof String Email) {
           Optional<User>  userOption = userRepository.findByEmail(Email);
            return userOption.orElseGet(User::new);
        }
        return null;
    }

    @Override
    @Transactional
    public void saveUserVerificationToken(User user, String verificationToken) {
        var verification_token = new VerificationToken(verificationToken, user);
        verificationTokenRepository.save(verification_token);
    }
    @Override
    @Transactional
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
    @Transactional
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
    private boolean isPasswordCorrect( String oldPassword) {
        User user = getCurrentUserDetails();
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
    @Override
    @Transactional
    public boolean ChangePassword(String oldPassword,String newPassword)
    {
        try{
            User user = getCurrentUserDetails();
            if (user != null && isPasswordCorrect(oldPassword)) {
                String Password = passwordEncoder.encode(newPassword);
                long Id = user.getId();
                userRepository.chancePasswordAccountById(Id,Password);
                return true;
            }else{
                return false;
            }
        }catch(RuntimeException e){
            throw new RuntimeException("Error : ",e);
        }
    }
    @Override
    public UserInfoResponse userDetail() {
        MapperGeneric<UserAddresses, UserInfoResponse.UserAddress> UserAddressMapper = new MapperGeneric<>();
        MapperGeneric<User, UserInfoResponse> UserMapper = new MapperGeneric<>();
        User user = getCurrentUserDetails();
        Optional<User> userOption = userRepository.findByEmailWithAddresses(user.getEmail());
        if(userOption.isPresent()){
            User userFull = userOption.get();
            List<UserInfoResponse.UserAddress> userAddressesList = new ArrayList<>();
            List<String>list = new ArrayList<>();
            for (Roles roles : userFull.getRoles()){
                list.add(roles.getName());
            }
            for(UserAddresses addresses : userFull.getUserAddresses())
            {
                UserInfoResponse.UserAddress userAddress = UserAddressMapper.ModelmapToDTO(addresses,UserInfoResponse.UserAddress.class);
                userAddressesList.add(userAddress);
            }
            UserInfoResponse userInfoResponse = UserMapper.ModelmapToDTO(userFull,UserInfoResponse.class);
            userInfoResponse.setRoles(list);
            userInfoResponse.setUserAddresses(userAddressesList);
            return userInfoResponse;
        }else{
            return new UserInfoResponse();
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
