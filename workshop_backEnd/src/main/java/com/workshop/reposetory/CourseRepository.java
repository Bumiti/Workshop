package com.workshop.reposetory;

import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Course findCourseById(Long id);
    @Transactional
    @Modifying
    @Query("UPDATE Course u SET u.isPublic = ?2 WHERE u.id = ?1")
    void updateById(long id,boolean isPublic);

    @Transactional
    @Modifying
    @Query("SELECT ce.enrolledStudent FROM CourseEnrollment ce WHERE ce.courses.id = :courseId")
    List<User> listUserInCourse(@Param("courseId") long courseId);

    @Transactional
    @Modifying
    @Query(" insert into CourseEnrollment (courses, enrolledStudent) " +
            "SELECT c, u FROM Course c JOIN User u ON u.id IN :studentIds " +
            "WHERE c.id = :courseId" )
    void addStudentsToCourse(@Param("courseId") long courseId, @Param("studentIds") List<Long> studentIds);
    @Transactional
    @Modifying
    @Query("SELECT c FROM Course c WHERE c.teacher.id = :teacherId")
    List<Course> listCoursebyTeacherId(Long teacherId);

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.name = :#{#course.name}, c.description = :#{#course.description}" +
            ", c.price = :#{#course.price}, c.startDate = :#{#course.startDate}, c.endDate = :#{#course.endDate}" +
            ", c.type = :#{#course.type} WHERE c.id = :Id")
    void updateCourse(@Param("Id") Long Id, @Param("course") Course course);




}
