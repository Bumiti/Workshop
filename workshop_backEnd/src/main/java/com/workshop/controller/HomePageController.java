package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/web/")
@RequiredArgsConstructor

@Tag(name = "HomeLanding Web FrontEnd", description = "Quản lý Dữ Liệu Hiển Thị ở Web")
public class HomePageController {
    private ResponseEntity<ApiResponse<?>> createResponse(HttpStatus httpStatus, String status, String message, Object data) {
        return ResponseEntity.status(httpStatus).body(new ApiResponse<>(status, message, data));
    }

    private final CourseService courseService;
    @Operation(summary = "Danh Sách Course")
    @GetMapping("course/list")
    public ResponseEntity<ApiResponse<?>> listCourse() {
        try {
            List<CourseResponses> courses = courseService.listCourseEnable();
            if (courses.isEmpty()) {
                return createResponse(HttpStatus.NOT_FOUND, "error", "List Courses is empty", null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "List of courses", courses);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "Course by Id")
    @GetMapping("course/detail")
    public ResponseEntity<ApiResponse<?>> CourseById(@RequestParam int id) {
        Long longId = (long) id;
        try {
            CourseResponses courses = courseService.courseById(longId);
            if (courses ==null) {
                return createResponse(HttpStatus.NOT_FOUND, "error", " Courses is Not Found Or Deactivate", null);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "success", "courses", courses);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }
    @Operation(summary = "CheckUserInCourse")
    @GetMapping("course/checkedUser")
    public ResponseEntity<ApiResponse<?>> checkUserInCourse(@RequestParam String user_email,@RequestParam int course_id) {

        Long courseId = (long) course_id;
        try {
            boolean res = courseService.checkUserInCourse(user_email,courseId);
            if (!res ) {
                return createResponse(HttpStatus.ACCEPTED, "false", " User Not In Course", false);
            } else {
                return createResponse(HttpStatus.ACCEPTED, "true", "User In Course", true);
            }
        } catch (Exception e) {
            return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error", "Internal Server Error", null);
        }
    }

}
