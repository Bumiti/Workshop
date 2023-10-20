package com.workshop.controller;

import com.workshop.config.ApiResponse;
import com.workshop.dto.CourseRequest;
import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Teacher Controller", description = "Quản Lý Tác Vụ Giáo Viên")
public class TeacherController {

    @Autowired
    private CourseService courseService;


    @Operation(summary = "Danh Sách Học Sinh Trong Khóa Học")
    @GetMapping("course/listStudent/{id}")
    public ResponseEntity<ApiResponse> ListStudentByCourser(@PathVariable Long id) {
        try {
            List<UserInforRespone> ListStudent = courseService.listStudentByCourse(id);
            if (ListStudent != null && !ListStudent.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse<>("success", "List of Students", ListStudent));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse<>("error", "No Students Found", null));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "An error occurred while listing students", null));
        }

    }

    @Operation(summary = "Danh Sách Khóa Học Của Giáo Viên")
    @GetMapping("course/list")
    public ResponseEntity<ApiResponse> ListCourseByTeacherId() {
        try {
            List<CourseRespones> ListCourse = courseService.listCourse();
            System.out.print(ListCourse);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse<>("success", "List of ListCourse", ListCourse));
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", exception.getMessage(), null));
        }
    }
    @Operation(summary = "Thêm khóa Học")
    @PostMapping("course/add")
    public ResponseEntity<ApiResponse> AddCourse(@RequestBody CourseRequest courseRequest) {
        if (courseRequest != null) {
            boolean result = courseService.addCourse(courseRequest);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(HttpStatus.CREATED.name(), "The Course has been Create ", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("error", "The Course cant been Create", null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                    ("error", "The Course NO_CONTENT", null));
        }
    }
    @Operation(summary = "Sửa khóa Học")
    @PutMapping("course/update/{id}")
    public ResponseEntity<ApiResponse> UpdateCourse(@PathVariable Long id, @RequestBody CourseRequest courseRequest) {
        if (id != null && courseRequest != null) {
            boolean result = courseService.updateCourse(id, courseRequest);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>(HttpStatus.CREATED.name(), "The Course has been Update ", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("error", "The Course cant been Update", null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                    ("error", "The Course NO_CONTENT", null));
        }
    }

    @Operation(summary = "Xóa khóa Học")
    @DeleteMapping("course/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCourse(@PathVariable Long id) {
        if (id != null) {
            boolean result = courseService.deleteCourse(id);
            if (result) {
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body(new ApiResponse<>(HttpStatus.ACCEPTED.name(), "The Course has been Delete ", null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>
                        ("error", "The Course cant been Delete", null));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ApiResponse<>
                    ("error", "The Course NO_CONTENT", null));
        }
    }
    @Operation(summary = "Thêm Học Sinh Vào khóa Học")
    @PostMapping("course/addListStudent/{id}/")
    public ResponseEntity<ApiResponse> AddEStudentCourse(@PathVariable Long id, @RequestBody List<Long> studentIds) {
        try {
            boolean result = courseService.AddEnrolledStudentsToCourseById(id, studentIds);
            if (result) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse<>("success", "List of Students has been added to the Course", result));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>("error", "No Students Found", null));
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("error", ex.getMessage(), null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>("error", "An error occurred while adding students to the course", null));
        }
    }
    @GetMapping("teacher/info")
    public ResponseEntity<ApiResponse<?>>teacherInfo(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                new ApiResponse<>("OK","Lấy thành công",null)
        );
    }

}

