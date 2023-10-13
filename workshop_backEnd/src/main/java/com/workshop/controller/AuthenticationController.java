package com.workshop.controller;

import com.workshop.authentication.*;
import com.workshop.config.ApiResponse;
import com.workshop.dao.UserServiceImpl;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.event.RegisterCompleteEvent;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.VerificationToken;
import com.workshop.reposetory.VerificationTokenRepository;
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


    private final AuthenticationService authenticationService;
    private final UserServiceImpl userServiceimpl;
    private final ApplicationEventPublisher publisher;
    private  final VerificationTokenRepository verificationTokenRepository;
    @Operation(summary = "Login Website Account")
    @PostMapping("/loginWeb")
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
    @Operation(summary = "Login Website OAuthentication")
    @PostMapping("/login0Authen")
    public ResponseEntity<ApiResponse> OAuthentication(@RequestBody OAuthenticationRequest OAuthen)
    {
        try {
            User user = userServiceimpl.SaveUserOAuthen(OAuthen);
            if (user != null) {
                AuthenticationRequest authenticationRequest = new AuthenticationRequest();
                authenticationRequest.setPassword(user.getEmail()).setEmail(user.getEmail());
                try {
                    AuthenticationResponse response = authenticationService.authenticationResponse(authenticationRequest);
                    if (response != null) {
                        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", "The data has been retrieved successfully", response));
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("error", "User not found", null));
                    }
                } catch (Exception authException) {
                    // Xử lý lỗi khi gọi authenticationService.authenticationResponse
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(authException.getMessage(), "Authentication service error", null));
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("error", "User not found", null));
            }
        } catch (Exception userException) {
            // Xử lý lỗi khi gọi userServiceimpl.SaveUserOAuthen
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(userException.getMessage(), "User service error", null));
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
    @Operation(summary ="verify Email" )
    @GetMapping("verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken thetoken = verificationTokenRepository.findByToken(token);
        if(thetoken.getUser().isEnable()){
            return "This Account has already been verified,please ! Login";
        }
        String verification = userServiceimpl.validate(token);
        if(verification.equalsIgnoreCase("valid")){
            return "Email verified Successfully, You can login";
        }
        return "Invalid verification token";
    }
    @GetMapping("endpont")
    public String endpont(){

        return "endpont";
    }
}