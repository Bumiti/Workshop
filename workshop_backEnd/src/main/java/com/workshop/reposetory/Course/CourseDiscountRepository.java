package com.workshop.reposetory.Course;

import com.workshop.model.courseModel.CourseDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDiscountRepository extends JpaRepository<CourseDiscount,Long> {
}
