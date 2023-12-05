package com.workshop.repositories.Course;

import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.QrToken;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QrCodeTickerRepository extends JpaRepository<QrToken,Long> {
    @Query("SELECT qt FROM QrToken qt WHERE qt.user = :user AND qt.course = :course")
    QrToken findQrTokensByUserAndCourse(@Param("user") User user, @Param("course") Course course);
}
