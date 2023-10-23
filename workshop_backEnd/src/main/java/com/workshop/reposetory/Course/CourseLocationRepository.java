package com.workshop.reposetory.Course;

import com.workshop.model.courseModel.CourseLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseLocationRepository extends JpaRepository<CourseLocation,Long> {
}
