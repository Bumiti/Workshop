package com.workshop.service;

import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.dto.WorkShopRespone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminService {
    boolean chanceIsEnableWithRoleAndId(Long Id);
    List<UserInforRespone> listAccountByRole(String role);
    List<CourseRespones> listCourse();
    List<WorkShopRespone> listWorkshop();
}
