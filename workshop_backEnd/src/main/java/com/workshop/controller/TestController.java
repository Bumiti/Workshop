package com.workshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test/")
public class TestController {

    @Operation(summary ="GetMapping" )
    @GetMapping
    public ResponseEntity<String>GetMapping(){
        return  ResponseEntity.ok("Check Ok");
    }
    @Operation(summary ="PutMapping" )
    @PutMapping
    public ResponseEntity<String>PutMapping(){
        return  ResponseEntity.ok("PutMapping Check Ok");
    }
    @Operation(summary ="DeleteMapping" )
    @DeleteMapping
    public ResponseEntity<String>Test(){
        return  ResponseEntity.ok("DeleteMapping Check Ok");
    }
}
