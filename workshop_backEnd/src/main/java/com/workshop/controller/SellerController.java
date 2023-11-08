package com.workshop.controller;
import com.workshop.config.ApiResponse;
import com.workshop.dto.CourseDTO.CourseRequest;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
public class SellerController {

@Autowired
    private CourseService courseService;
    @GetMapping("")
    public ResponseEntity<String> sellerPage()
    {
        return ResponseEntity.ok("Seller EndPoint");
    }
    @PostMapping("addCourse")
    public ResponseEntity<ApiResponse> addCourse(@RequestBody CourseRequest courseRequest)
    {
        if(courseRequest!=null){
           boolean result =  courseService.addCourse(courseRequest);
           if(result){
               return ResponseEntity.status(HttpStatus.CREATED)
                       .body( new ApiResponse<>(HttpStatus.CREATED.name(),"The Course has been Create ",null));
           }else{
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                       ("error", "The Course cant been Create",  null));
           }
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                    ("error", "The Course NO_CONTENT",  null));
        }

    }


}
