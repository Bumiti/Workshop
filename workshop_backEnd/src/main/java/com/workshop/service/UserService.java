package com.workshop.service;

import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User SaveUser(UserRegisterRequest user);
    User SaveSeller(UserRegisterRequest user);
    Roles SaveRoles(Roles role);
    Void AddRoleToUser(String user_name,String role_name);
    User getCurrentUserDetails();
    void saveUserVerificationToken(User user, String verificationToken);

    String validate(String token);
}
