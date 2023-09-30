package com.workshop.service;

import com.workshop.authentication.*;
import com.workshop.jwtservice.JwtService;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private  final RoleCustomerReposetory RoleCustomerReposetory;
    private final JwtService jwtService;
    public AuthenticationResponse authenticationResponse(AuthenticationRequest authenticationRequest){


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow();
        List<Roles> role = null;
        if(user!=null){ role = RoleCustomerReposetory.getRole(user);}

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Roles> set = new HashSet<>();
        role.stream().forEach(c->set.add(new Roles(c.getName())));
        user.setRoles(set);
        set.stream().forEach(i->authorities.add(new SimpleGrantedAuthority(i.getName())));

        var jwtToken = jwtService.generateAccessToken(user,authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(user,authorities);
        return AuthenticationResponse.builder().token(jwtToken).refreshToken(jwtRefreshToken).build();
    }
}