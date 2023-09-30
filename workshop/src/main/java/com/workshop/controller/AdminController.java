package com.workshop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
public class AdminController {

    @GetMapping("")
    public ResponseEntity<String> adminPage()
    {
        return ResponseEntity.ok("Admin EndPoint");
    }
}
