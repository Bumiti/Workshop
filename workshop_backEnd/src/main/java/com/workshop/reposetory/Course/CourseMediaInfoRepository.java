package com.workshop.reposetory.Course;

import com.workshop.model.courseModel.CourseMediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMediaInfoRepository extends JpaRepository<CourseMediaInfo,Long> {


}
