package com.workshop.reposetory;

import com.workshop.dto.WorkShopRespone;
import com.workshop.model.workshopModel.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public interface WorkShopRepository extends JpaRepository<Workshop,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Workshop w SET w.isApproved = ?2 WHERE w.id = ?1")
    void updateById(long id,boolean isPublic);
    @Transactional
    @Modifying
    @Query("SELECT w from Workshop w join w.teacher t join w.enrolledStudents st join w.location l join w.workshopDiscounts")
    List<Workshop> getListWorkShop();
}
