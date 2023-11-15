package com.workshop.repositories.Course;

import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.CourseEnrollment;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment,Long> {

    CourseEnrollment findByCoursesAndEnrolledStudent(Course course, User user);


    @Query("SELECT COUNT(c) > 0 FROM CourseEnrollment c WHERE c.enrolledStudent.id = :user_id AND c.courses.id = :course_id")
    boolean checkUserInCourse(@Param("user_id") Long user_id, @Param("course_id") Long course_id);

}
