package com.workshop.service;

import com.workshop.dto.CourseDTO.CourseRespones;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.dto.WorkShopRespone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminService {
    //----------------------------------------------------Account----------------------------------------------------//
    boolean chanceIsEnableWithRoleAndId(Long Id);
    boolean deleteAddressOfUser(Long userId,Long userAddressId);
    UserInfoResponse findUserById(Long userId);
    List<UserInfoResponse> listAccountByRole(String role);
    List<UserInfoResponse> listAccount();





    //----------------------------------------------------Account----------------------------------------------------//
    List<CourseRespones> listCourse();
    List<WorkShopRespone> listWorkshop();
}
