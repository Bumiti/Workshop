package com.workshop.service;

import com.workshop.dto.CourseDTO.CourseRequest;
import com.workshop.dto.CourseDTO.CourseRespones;
import com.workshop.dto.useDTO.UserInfoResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseService {
    boolean addCourse(CourseRequest courseRequest);
    boolean updateCourse(Long id, CourseRequest courseRequest);
    boolean deleteCourse(Long id);
    boolean settingStatusCourse(Long id);
    List<UserInfoResponse> listStudentByCourse(Long id);
    List<CourseRespones> listCourse();
    boolean AddEnrolledStudentsToCourseById(Long Course_id,List<Long> List_Student_id);

}
