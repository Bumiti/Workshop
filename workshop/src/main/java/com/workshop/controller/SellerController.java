package com.workshop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
public class SellerController {
    @GetMapping("")
    public ResponseEntity<String> sellerPage()
    {
        return ResponseEntity.ok("Seller EndPoint");
    }
}
