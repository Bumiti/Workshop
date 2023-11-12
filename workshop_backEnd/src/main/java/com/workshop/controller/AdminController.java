package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dto.CourseDTO.CourseRespones;
import com.workshop.dto.RequestResponse;
import com.workshop.dto.useDTO.UserEditRequest;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.service.AdminService;
import com.workshop.service.CourseService;
import com.workshop.service.RequestService;
import com.workshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@Tag(name = "Admin Area Controller", description = "Quản Lý Tác Vụ Admin Service")
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    private final AdminService adminService;
    private final CourseService courseService;
    private final RequestService requestService;
    private final UserService userService;
    private ResponseEntity<ApiResponse<?>> createResponse(HttpStatus httpStatus, String status, String message, Object data) {
        return ResponseEntity.status(httpStatus).body(new ApiResponse<>(status, message, data));
    }
    @Operation(summary = "Danh Sách Course")
    @GetMapping("course/list")
    public ResponseEntity<ApiResponse<?>> listCourse() {
        try {
            List<CourseRespones> courses = adminService.listCourse();
            if (courses.isEmpty()) {
                return createResponse(HttpStatus.NOT_FOUND, "error", "List Courses is empty", null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of courses", courses);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Danh Sách Request")
    @GetMapping("request/list")
    public ResponseEntity<ApiResponse<?>> listRequest() {
        try {
            List<RequestResponse> requestResponseList = requestService.ListRequest();
            if (requestResponseList.isEmpty()) {
                return createResponse(HttpStatus.ACCEPTED, "success", "List Request is empty", null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of Request", requestResponseList);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Course")
    @PostMapping("course/status")
    public ResponseEntity<ApiResponse<?>> activeCourse(@RequestParam int id) {
        try {
            Long longId = (long) id;
            if (longId >0) {
                boolean result = courseService.settingStatusCourse(longId);
                if (result) {
                    return createResponse(HttpStatus.ACCEPTED, "success", "The Course Status has been changed", null);
                } else {
                    return createResponse(HttpStatus.BAD_REQUEST, "error", "Failed to change course status", null);
                }
            } else {
                return createResponse(HttpStatus.NO_CONTENT, "error", "Invalid request: Course ID is missing", null);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Tìm Account bằng Role")
    @GetMapping("user/listUserByRole")
    public ResponseEntity<ApiResponse<?>> listAccountByRole(@RequestParam(name = "role") String role) {
        try {
            List<UserInfoResponse> accounts = adminService.listAccountByRole(role);
            if (accounts.isEmpty()) {
                return createResponse(HttpStatus.NOT_FOUND, "error", "No account found with role " + role, null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of " + role, accounts);
            }
        }catch(Exception e){
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Tìm Account bằng Id")
    @GetMapping("user/findById")
    public ResponseEntity<ApiResponse<?>> AccountById(@RequestParam(name = "id") int id) {
        try {
            Long longId = (long) id;
           UserInfoResponse accounts = adminService.findUserById(longId);
            if (accounts!=null) {
                return createResponse(HttpStatus.ACCEPTED, "success", "User by :" + id, accounts);
            } else {
                return createResponse(HttpStatus.NOT_FOUND, "error", "No account found with Id " + id, null);
            }
        }catch(Exception e){
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Danh sách Account")
    @GetMapping("user/listUser")
    public ResponseEntity<ApiResponse<?>> listAccount() {
        try {
            List<UserInfoResponse> accounts = adminService.listAccount();
            if (accounts.isEmpty()) {
                return createResponse(HttpStatus.NOT_FOUND, "error", "No account found with role " , null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of " , accounts);
            }
        }catch(Exception e){
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Account")
    @PutMapping("user/changeStatus")
    public ResponseEntity<ApiResponse<?>> activeUserByRole(@RequestParam int id) {
        try {
            Long longId = (long) id;
            boolean result = adminService.chanceIsEnableWithRoleAndId(longId);
            if (result) {
                return createResponse(HttpStatus.ACCEPTED, "success", "Account Status has been Chance", null);
            } else {
                return createResponse(HttpStatus.BAD_REQUEST, "error", "Failed to activate teacher", null);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage(), null);
        }
    }
    @Operation(summary = "Sửa thông tin cá nhân Admin")
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

}

