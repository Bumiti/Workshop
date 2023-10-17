package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dao.AdminServiceImpl;
import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.dto.WorkshopRespones;
import com.workshop.model.workshopModel.Workshop;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;
    @Autowired
    private CourseService courseService;

    private ResponseEntity<ApiResponse> handleResponse(HttpStatus status, String message, Object data) {
        return ResponseEntity.status(status).body(new com.workshop.config.ApiResponse(status.name(), message, data));
    }

    @GetMapping("listCourse")
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

    @GetMapping("listWorkshop")
    public ResponseEntity<ApiResponse> listWorkshop() {
        try {
            List<WorkshopRespones> workshops = adminService.listWorkshop();
            if (workshops.isEmpty()) {
                return handleResponse(HttpStatus.NOT_FOUND, "No workshops found", null);
            } else {
                return handleResponse(HttpStatus.ACCEPTED, "List of workshops", workshops);
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
    @PostMapping("activeTeacher")
    public ResponseEntity<ApiResponse> activeTeacher(@RequestParam Long id) {
        try {
            boolean result = true; // Giả sử xử lý thành công
            if (result) {
                return handleResponse(HttpStatus.ACCEPTED, "Teacher has been activated", null);
            } else {
                return handleResponse(HttpStatus.BAD_REQUEST, "Failed to activate teacher", null);
            }
        } catch (Exception e) {
            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
        }
    }

//    @PostMapping("activeWorkshop")
//    public ResponseEntity<ApiResponse> activeWorkshop(@RequestParam Long id) {
//        try {
//            // Xử lý logic activeWorkshop ở đây
//            boolean result = true; // Giả sử xử lý thành công
//            if (result) {
//                return handleResponse(HttpStatus.ACCEPTED, "Workshop has been activated", null);
//            } else {
//                return handleResponse(HttpStatus.BAD_REQUEST, "Failed to activate workshop", null);
//            }
//        } catch (Exception e) {
//            return handleResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", null);
//        }
//    }

    @PostMapping("activeCourse")
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
}

