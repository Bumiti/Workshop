package com.workshop.jwtservice;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.workshop.model.userModel.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
@Service
public class JwtService {
    private static final String Secret_key = "Service_QuanKZ_0401_1995";

    public String generateAccessToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("username",user.getUser_name())
                .withClaim("full_name",user.getFull_name())
                .withClaim("image","se_update_hinh_phia_fire_base")
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    public String generateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("username",user.getUser_name())
                .withClaim("full_name",user.getFull_name())
                .withClaim("image","se_update_hinh_phia_fire_base")
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 7000))
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }
}
