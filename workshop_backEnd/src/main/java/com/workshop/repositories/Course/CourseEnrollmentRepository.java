package com.workshop.repositories.Course;

import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.CourseEnrollment;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment,Long> {

    CourseEnrollment findByCoursesAndEnrolledStudent(Course course, User user);
}
