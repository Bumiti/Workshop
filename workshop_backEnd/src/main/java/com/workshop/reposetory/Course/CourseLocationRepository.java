package com.workshop.reposetory.Course;

import com.workshop.model.courseModel.CourseLocation;
import com.workshop.model.courseModel.CourseMediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseLocationRepository extends JpaRepository<CourseLocation,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE CourseLocation c SET c.Area = :#{#courseLocation.area}, c.schedule_Date = :#{#courseLocation.schedule_Date} WHERE c.id = :Id")
    void updateCourseLocation(@Param("Id") Long Id, @Param("courseLocation") CourseLocation courseLocation);

}
