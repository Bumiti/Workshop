package com.workshop.service;

import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.model.workshopModel.Workshop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminService {
    List<UserInforRespone> ListStudent();
    List<UserInforRespone> ListTeacher();
    List<CourseRespones> ListCourse();
    List<Workshop> ListWorkshop();
}
