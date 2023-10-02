package com.workshop.controller;

import com.workshop.authentication.*;
import com.workshop.config.ApiResponse;
import com.workshop.dao.UserServiceImpl;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserServiceImpl userServiceimpl;
    @Operation(summary = "Login Validation For All Account")
    @PostMapping("")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthenticationRequest authenticationRequest)
    {
        AuthenticationResponse response = authenticationService.authenticationResponse(authenticationRequest);
        if(response!=null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>
                    ("success", "The data has been retrieved successfully", authenticationService.authenticationResponse(authenticationRequest)));
        }else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>
                    ("error", "User not found",  null));
        }

    }
    @Operation(summary ="Register Buyer" )
    @PostMapping("register/user")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody UserRegisterRequest userRegisterRequest)
    {
        if(userRegisterRequest!=null){
            userServiceimpl.SaveUser(userRegisterRequest);
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Operation(summary ="Register Seller" )
    @PostMapping("register/seller")
    public ResponseEntity<HttpStatus> registerSeller(@RequestBody UserRegisterRequest userRegisterRequest)
    {
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}