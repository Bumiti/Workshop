package com.workshop.reposetory;

import com.workshop.model.courseModel.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Course u SET u.isPublic = ?2 WHERE u.id = ?1")
    void updateById(long id,boolean isPublic);

}
