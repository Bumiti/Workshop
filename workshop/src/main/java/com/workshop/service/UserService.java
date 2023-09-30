package com.workshop.service;

import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService {
    User SaveUser(User user);
    Roles SaveRoles(Roles role);
    Void AddRoleToUser(String user_name,String role_name);
}
