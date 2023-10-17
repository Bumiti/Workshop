package com.workshop.service;

import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.dto.WorkshopRespones;
import com.workshop.model.workshopModel.Workshop;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminService {
    List<UserInforRespone> listAccountByRole(String role);
    List<CourseRespones> listCourse();
    List<WorkshopRespones> listWorkshop();
}
