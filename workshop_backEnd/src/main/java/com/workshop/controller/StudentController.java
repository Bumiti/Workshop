package com.workshop.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
@Tag(name = "Student Controller", description = "Controller Manager Student Service")
public class StudentController {


}