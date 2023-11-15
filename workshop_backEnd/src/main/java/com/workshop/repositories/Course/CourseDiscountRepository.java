package com.workshop.repositories.Course;

import com.workshop.model.Discount;
import com.workshop.model.courseModel.CourseDiscount;
import com.workshop.model.courseModel.CourseEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CourseDiscountRepository extends JpaRepository<CourseDiscount,Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM CourseDiscount cd WHERE cd.discount.id = :discountId AND cd.quantity = :newQuantity")
    void deleteCourseDiscounts(@Param("discountId") Long discountId, @Param("newQuantity") int newQuantity);


    void deleteCourseDiscountByDiscount(Discount discount);
    @Transactional
    void deleteByCode(String Code);


    CourseDiscount findByCode(String code);
}
