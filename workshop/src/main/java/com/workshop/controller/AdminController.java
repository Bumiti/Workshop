package com.workshop.controller;

import com.workshop.dao.UserServiceImpl;
import com.workshop.model.userModel.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @GetMapping("info")
    public ResponseEntity<User> adminPage()
    {
        User user =  userService.getCurrentUserDetails();
            return ResponseEntity.ok(user);
    }

    @PostMapping ("aa")
    public ResponseEntity<User> Add()
    {
        User user =  userService.getCurrentUserDetails();
        return ResponseEntity.ok(user);
    }
}
