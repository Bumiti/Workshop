package com.workshop.service;

import com.workshop.dto.CourseRequest;
import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseService {
    boolean AddCourse(CourseRequest courseRequest);
    boolean UpdateCourse(Long id, CourseRequest courseRequest);
    boolean DeleteCourse(Long id);
    boolean SettingStatusCourse(Long id);
    List<UserInforRespone> ListStudentByCourse(Long id);
    List<CourseRespones> ListCourse();
    boolean AddEnrolledStudentsToCourseById(Long Course_id,List<Long> List_Student_id);

}
