package com.workshop.service;

import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.dto.useDTO.UserInfoResponse;
<<<<<<< HEAD
=======
import com.workshop.dto.WorkShopRespone;
import com.workshop.model.userModel.User;
>>>>>>> 0d863eee00bb51bf16662ef47be2d513d0350cd3
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

    User getCurrentUserDetails();

    UserInfoResponse userDetail();
    //----------------------------------------------------Account----------------------------------------------------//
    //----------------------------------------------------Course----------------------------------------------------//
    List<CourseResponses> listCourse();

    //----------------------------------------------------Course----------------------------------------------------//

}
