package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dto.RequestDTO.RequestDTO;
import com.workshop.dto.useDTO.UserEditRequest;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.model.Request;
import com.workshop.service.CourseService;
import com.workshop.service.RequestService;
import com.workshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@SecurityRequirement(name ="bearerAuth")
@Tag(name = "Student Controller", description = "Quản Lý Tác Vụ Học Sinh")
public class StudentController {

    private final UserService userService;
    private final RequestService requestService;
    private final CourseService courseService;
    @Operation(summary = "Lấy thông tin cá nhân Học Sinh")
    @GetMapping("/detail")
    public ResponseEntity<ApiResponse<?>> UserDetail() {
        UserInfoResponse userInfoResponse = userService.userDetail();
        if(userInfoResponse !=null){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                    ("Success", "Your Info is ", userInfoResponse));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                    ("Success", "Your Info is ", null));
        }
    }
    @Operation(summary = "Đổi mật khẩu")
    @PutMapping("changePassword")
    public ResponseEntity<ApiResponse<?>> changePassword(@RequestParam String oldPassword, @RequestParam String newPassword) {
        try {
            boolean result = userService.ChangePassword(oldPassword, newPassword);
            if (result) {
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", "Password has been changed", null));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>("failure", "Password change failed", null));
            }
        } catch (Exception authException) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>(authException.getMessage(), "Authentication service error", null));
        }
    }
    @Operation(summary = "Sửa thông tin Student")
    @PutMapping("/edit")
    public ResponseEntity<ApiResponse<?>> editUser(@RequestBody UserEditRequest userEditRequest) {
        try {
            boolean result = userService.EditUser(userEditRequest);
            if (result) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                        ("Success", "Your Info Has Been Changed", null));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>
                        ("Error", "Please check again", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>
                    ("Error", "An error occurred: " + e.getMessage(), null));
        }
    }

    @Operation(summary = "Nạp Tiền Vào Tài Khoản")
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<?>> Deposit(@RequestBody RequestDTO requestDTO) {
        try {
            requestDTO.setType("DEPOSIT");
            String result =  requestService.createRequestOptions(requestDTO);
            if (result.equals("APPROVED")) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                        ("Success", "Your Request APPROVED", null));
            } else if(result.equals("PENDING") ){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                        ("pending", "Your Request PENDING", null));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("cancel", "Your Request REJECTED", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>
                    ("Error", "An error occurred: " + e.getMessage(), null));
        }
    }
    @Operation(summary = "Mua khóa Học")
    @PostMapping("/byCourse")
    public ResponseEntity<ApiResponse<?>> ByCourse(@RequestBody RequestDTO requestDTO) {
        try {

            requestDTO.setType("BUY_COURSE");
            String result =  requestService.createRequestOptions(requestDTO);
            if (result.equals("APPROVED")) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                        ("Success", "Your Request ACCEPTED", null));
            } else if(result.equals("PENDING") ){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                        ("pending", "Your Request PENDING", null));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("cancel", "Your Request REJECTED", null));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>
                    ("Error", "An error occurred: " + e.getMessage(), null));
        }
    }
    @Operation(summary = "Check Code Course Discount")
    @GetMapping("/checkDiscount")
    public ResponseEntity<ApiResponse<?>> CheckCodeDiscountCourese(@RequestParam String code) {
        try {

            int result =  courseService.checkCodeDiscount(code);
            if (result>0) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse<>
                        ("Success", "Your Code is Active", result));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>
                        ("not_found", "Your Code : "+code+ " is OutDate", null));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse<>
                    ("Error", "An error occurred: " + e.getMessage(), null));
        }
    }
}