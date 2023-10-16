package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dao.AdminServiceImpl;
import com.workshop.dao.UserServiceImpl;
import com.workshop.model.userModel.User;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
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
    private AdminServiceImpl adminService;
    @Autowired
    private CourseService courseService;
    @GetMapping("listCourse")
    public ResponseEntity<ApiResponse> listCourse()
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",adminService.ListCourse()));
    }
    @GetMapping("listWorkshop")
    public ResponseEntity<ApiResponse> listWorkshop()
    {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",adminService.ListWorkshop()));
    }
    @GetMapping("listStudent")
    public ResponseEntity<ApiResponse> listStudent()
    {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",adminService.ListStudent()));
    }
    @GetMapping("listTeacher")
    public ResponseEntity<ApiResponse> listTeacher()
    {

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",adminService.ListTeacher()));
    }










    @PostMapping ("activeTeacher")
    public ResponseEntity<ApiResponse> activeTeacher(@RequestParam Long id)
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",null));
    }
    @PostMapping ("activeWorkshop")
    public ResponseEntity<ApiResponse> activeWorkshop(@RequestParam Long id)
    {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body( new com.workshop.config.ApiResponse<>("ACCEPTED","The Course Status has been Chance ",null));
    }
    @PostMapping ("activeCourse")
    public ResponseEntity<ApiResponse> activeCourse(@RequestParam Long id)
    {
        if(id!=null){
            boolean result =  courseService.settingStatusCourse(id);
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
