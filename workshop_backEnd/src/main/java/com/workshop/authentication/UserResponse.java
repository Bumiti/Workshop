package com.workshop.authentication;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserResponse {
    private String name;
    private String user_name;
    private String email;
    private String image;
    private List<String> roles;
    private String accessToken;
    private String refreshToken;
}
