package com.workshop.controller;
import com.workshop.config.ApiResponse;
import com.workshop.dao.AdminServiceImpl;
import com.workshop.dto.*;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@Tag(name = "Admin Area Controller", description = "Controller Admin Manager Web Service")
@SecurityRequirement(name = "bearerAuth")

public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private CourseService courseService;

    private ResponseEntity<ApiResponse> handleResponse(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(new com.workshop.config.ApiResponse(status.name(), message, data));
    }
    @Operation(summary = "Danh Sách Course")
    @GetMapping("course/list")
    public ResponseEntity<ApiResponse> listCourse() {
        try {
            List<CourseRespones> courses = adminService.listCourse();
            if (courses.isEmpty()) {
                return handleResponse(HttpStatus.NOT_FOUND, "No courses found", null);
            } else {
                return handleResponse(HttpStatus.ACCEPTED, "List of courses", courses);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Course")
    @PostMapping("course/status")
    public ResponseEntity<ApiResponse> activeCourse(@RequestParam Long id) {
        try {
            if (id != null) {
                boolean result = courseService.settingStatusCourse(id);
                if (result) {
                    return handleResponse(HttpStatus.ACCEPTED, "The Course Status has been changed", null);
                } else {
                    return handleResponse(HttpStatus.BAD_REQUEST, "Failed to change course status", null);
                }
            } else {
                return handleResponse(HttpStatus.NO_CONTENT, "Invalid request: Course ID is missing", null);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Course")
    @DeleteMapping("course/delete")
    public ResponseEntity<ApiResponse> removeCourse(@RequestParam Long id){
        try {
            if (id != null) {
                boolean result = courseService.settingStatusCourse(id);
                if (result) {
                    return handleResponse(HttpStatus.ACCEPTED, "The Course Status has been changed", null);
                } else {
                    return handleResponse(HttpStatus.BAD_REQUEST, "Failed to change course status", null);
                }
            } else {
                return handleResponse(HttpStatus.NO_CONTENT, "Invalid request: Course ID is missing", null);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }




    @GetMapping("listWorkshop")
    public ResponseEntity<ApiResponse> listWorkshop() {
        try {
            List<WorkShopRespone> workshops = adminService.listWorkshop();
            if (workshops.isEmpty()) {
                return handleResponse(HttpStatus.NOT_FOUND, "No workshops found", null);
            } else {
                return handleResponse(HttpStatus.ACCEPTED, "List of workshops", workshops);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    @Operation(summary = "Admin update Infor Workshop")
    @GetMapping("workshop/updateinfor")
    public ResponseEntity<ApiResponse> checkWorkshop(@RequestBody WorkShopRequest request) {
        try {

            if (request !=null) {
                return handleResponse(HttpStatus.NOT_FOUND, "No workshops found", null);
            } else {
                return handleResponse(HttpStatus.ACCEPTED, "List of workshops", null);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }
    @Operation(summary = "Tìm Account bằng Role")
    @GetMapping("listAccountByRole")
    public ResponseEntity<ApiResponse> listAccountByRole(@RequestParam(name = "role") String role) {
        try {
            List<UserInforRespone> accounts = adminService.listAccountByRole(role);
            if (accounts.isEmpty()) {
                return handleResponse(HttpStatus.NOT_FOUND, "No account found with role " + role, null);
            } else {
                return handleResponse(HttpStatus.ACCEPTED, "List of  " + role, accounts);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }
    @Operation(summary = "Thay đổi trạng thái Account")

    @PostMapping("controlAccountByRole")
    public ResponseEntity<ApiResponse> activeUserByRole(@RequestParam Long id) {
        try {
            boolean result = adminService.chanceIsEnableWithRoleAndId(id); // Giả sử xử lý thành công
            if (result) {
                return handleResponse(HttpStatus.ACCEPTED, "Account Status has been Chance", null);
            } else {
                return handleResponse(HttpStatus.BAD_REQUEST, "Failed to activate teacher", null);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

}

