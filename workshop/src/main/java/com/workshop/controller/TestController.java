package com.workshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/")
public class TestController {

    @GetMapping
    public ResponseEntity<String>Test(){
        return  ResponseEntity.ok("Check Ok");
    }
}
