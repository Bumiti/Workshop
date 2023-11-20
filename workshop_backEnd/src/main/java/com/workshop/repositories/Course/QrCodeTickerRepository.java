package com.workshop.repositories.Course;

import com.workshop.model.courseModel.QrToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QrCodeTickerRepository extends JpaRepository<QrToken,Long> {
}
