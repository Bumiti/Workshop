package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dto.CourseDTO.CourseRespones;
import com.workshop.dto.RequestResponse;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.service.AdminService;
import com.workshop.service.CourseService;
import com.workshop.service.RequestService;
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
                return createResponse(HttpStatus.NOT_FOUND, "error", "List Request is empty", null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of Request", requestResponseList);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Course")
    @PostMapping("course/status")
    public ResponseEntity<ApiResponse<?>> activeCourse(@RequestParam Long id) {
        try {
            if (id != null) {
                boolean result = courseService.settingStatusCourse(id);
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
    @Operation(summary = "Xóa (Disable) Course")
    @DeleteMapping("course/delete")
    public ResponseEntity<ApiResponse<?>> removeCourse(@RequestParam Long id) {
        try {
            if (id != null) {
                boolean result = courseService.settingStatusCourse(id);
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
//    @Operation(summary = "Danh Sách Workshop")
//    @GetMapping("workShop/list")
//    public ResponseEntity<ApiResponse<?>> listWorkshop() {
//        try {
//            List<WorkShopRespone> workshops = adminService.listWorkshop();
//            if (workshops.isEmpty()) {
//                return createResponse(HttpStatus.NOT_FOUND, "error", "No workshops found", null);
//            } else {
//                return createResponse(HttpStatus.ACCEPTED, "success", "List of workshops", workshops);
//            }
//        } catch (Exception e) {
//            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage(), null);
//        }
//    }

//    @Operation(summary = "Admin update Infor Workshop")
//    @GetMapping("workshop/updateinfor")
//    public ResponseEntity<ApiResponse<?>> checkWorkshop(@RequestBody WorkShopRequest request) {
//        try {
//            if (request != null) {
//                return createResponse(HttpStatus.ACCEPTED, "success", "List of workshops", null);
//            } else {
//                return createResponse(HttpStatus.NOT_FOUND, "error", "No workshops found", null);
//            }
//        } catch (Exception e) {
//            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage(), null);
//        }
//    }
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
    @PostMapping("user/changeStatus")
    public ResponseEntity<ApiResponse<?>> activeUserByRole(@RequestParam Long id) {
        try {
            boolean result = adminService.chanceIsEnableWithRoleAndId(id);
            if (result) {
                return createResponse(HttpStatus.ACCEPTED, "success", "Account Status has been Chance", null);
            } else {
                return createResponse(HttpStatus.BAD_REQUEST, "error", "Failed to activate teacher", null);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", e.getMessage(), null);
        }
    }
}

