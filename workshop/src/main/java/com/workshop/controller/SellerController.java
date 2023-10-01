package com.workshop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
