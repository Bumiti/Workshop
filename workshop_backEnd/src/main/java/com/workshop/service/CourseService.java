package com.workshop.service;

import com.workshop.dto.CourseRequest;
import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseService {
    boolean addCourse(CourseRequest courseRequest);
    boolean updateCourse(Long id, CourseRequest courseRequest);
    boolean deleteCourse(Long id);
    boolean settingStatusCourse(Long id);
    List<UserInforRespone> listStudentByCourse(Long id);
    List<CourseRespones> listCourse();
    boolean AddEnrolledStudentsToCourseById(Long Course_id,List<Long> List_Student_id);

}
