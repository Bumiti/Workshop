package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dao.UserServiceImpl;
import com.workshop.dto.CourseRequest;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CourseService courseService;
    @GetMapping("info")
    public ResponseEntity<User> adminPage()
    {
        User user =  userService.getCurrentUserDetails();
            return ResponseEntity.ok(user);
    }
    @PostMapping ("aa")
    public ResponseEntity<User> Add()
    {
        User user =  userService.getCurrentUserDetails();
        return ResponseEntity.ok(user);
    }
    @PostMapping ("ActiveCourse")
    public ResponseEntity<ApiResponse> Add(@RequestParam Long id)
    {
        if(id!=null){
            boolean result =  courseService.SettingStatusCourse(id);
            if(result){
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",null));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("error", "The Course cant been Active",  null));
            }
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                    ("error", "The Course NO_CONTENT",  null));
        }
    }
}
