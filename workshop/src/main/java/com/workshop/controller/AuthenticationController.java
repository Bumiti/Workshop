package com.workshop.controller;

import com.workshop.authentication.*;
import com.workshop.config.ApiResponse;
import com.workshop.dao.UserServiceImpl;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.event.RegisterCompleteEvent;
import com.workshop.model.userModel.User;
import com.workshop.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher publisher;
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
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserRegisterRequest userRegisterRequest, final HttpServletRequest request)
    {
        if(userRegisterRequest!=null){
            User user = userServiceimpl.SaveUser(userRegisterRequest);
            publisher.publishEvent(new RegisterCompleteEvent(user,applicationUrl(request)));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                    ("Success", "please check your Email to complete your registration",  null));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>
                    ("Error", "please check your Email Again",  null));
        }
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

    @Operation(summary ="Register Seller" )
    @PostMapping("register/seller")
    public ResponseEntity<HttpStatus> registerSeller(@RequestBody UserRegisterRequest userRegisterRequest)
    {
        if(userRegisterRequest!=null){
            userServiceimpl.SaveSeller(userRegisterRequest);
        }
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}