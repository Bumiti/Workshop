package com.workshop.reposetory.Course;

import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.CourseMediaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseMediaInfoRepository extends JpaRepository<CourseMediaInfo,Long> {


    @Transactional
    @Modifying
    @Query("UPDATE CourseMediaInfo c SET c.urlImage = :#{#courseMediaInfo.urlImage}, c.urlMedia = :#{#courseMediaInfo.urlMedia} WHERE c.id = :Id")
    void updateCourse(@Param("Id") Long Id, @Param("courseMediaInfo") CourseMediaInfo courseMediaInfo);
}
