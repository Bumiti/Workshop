package com.workshop.authentication;

import com.workshop.model.userModel.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse<T>  {
//    private String token;
//    private String refreshToken;
    private T user;
}
