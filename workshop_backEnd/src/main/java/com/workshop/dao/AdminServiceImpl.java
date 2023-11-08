package com.workshop.dao;

import com.workshop.config.MapperGeneric;
import com.workshop.dto.*;
import com.workshop.dto.CourseDTO.CourseRespones;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.model.Discount;
import com.workshop.model.Location;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.Roles;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.UserAddresses;
import com.workshop.repositories.*;
import com.workshop.repositories.Course.CourseRepository;
import com.workshop.repositories.User.UserAddressRepository;
import com.workshop.repositories.User.UserRepository;
import com.workshop.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private  final UserAddressRepository userAddressRepository;
    private final CourseRepository courseRepository;
    private final WorkShopRepository workShopRepository;
    @Override
    public boolean chanceIsEnableWithRoleAndId(Long Id) {
        try {
            int result = userRepository.chanceStatusAccountById(Id);
            return result > 0;
        }
        catch (Exception exception) {
            throw new RuntimeException("Error: " + exception);
        }
    }

    @Override
    public boolean deleteAddressOfUser(Long userId, Long userAddressId)
    {
        try
        {
           Optional<User> user = userRepository.findById(userId);
           if(user.isPresent()){
               User user1 = user.get();
               userAddressRepository.deleteUserAddressesByUserAndId(user1,userAddressId);
               return true;
           }
           else{
               return false;
           }
        } catch (Exception exception) {
            throw new RuntimeException("Error: " + exception);
        }
    }

    @Override
    public boolean deleteUser(Long userId) {
        return false;
    }

    @Override
    public List<UserInfoResponse> listAccountByRole(String role) {
        List<UserInfoResponse> listUserInfoResponse = new ArrayList<>();
        List<User> ListUser = userRepository.findUsersByRoleName(role);
        for(User user : ListUser){
            MapperGeneric<User,UserInfoResponse> userMapper = new MapperGeneric<>();
            UserInfoResponse userInfoResponse =  userMapper.ModelmapToDTO(user,UserInfoResponse.class);
            List<UserInfoResponse.UserAddress> listUserAddressResponse = new ArrayList<>();
            for(UserAddresses userAddresses : user.getUserAddresses()){
                MapperGeneric<UserAddresses,UserInfoResponse.UserAddress> userAddressMapper = new MapperGeneric<>();
                UserInfoResponse.UserAddress userAddress = userAddressMapper.ModelmapToDTO(userAddresses,UserInfoResponse.UserAddress.class);
                listUserAddressResponse.add(userAddress);
            }
            List<String>roleList =new ArrayList<>();
            for(Roles roles : user.getRoles()){
                roleList.add(roles.getName());
            }
            userInfoResponse.setRoles(roleList);
            userInfoResponse.setUserAddresses(listUserAddressResponse);
            listUserInfoResponse.add(userInfoResponse);
        }
        return listUserInfoResponse;
    }

    @Override
    public List<UserInfoResponse> listAccount() {
        List<UserInfoResponse> listUserInfoResponse = new ArrayList<>();
        List<User> ListUser = userRepository.findUsersNonAdmin();
        for(User user : ListUser){
            MapperGeneric<User,UserInfoResponse> userMapper = new MapperGeneric<>();
            UserInfoResponse userInfoResponse =  userMapper.ModelmapToDTO(user,UserInfoResponse.class);
            List<UserInfoResponse.UserAddress> listUserAddressResponse = new ArrayList<>();
            for(UserAddresses userAddresses : user.getUserAddresses()){
                MapperGeneric<UserAddresses,UserInfoResponse.UserAddress> userAddressMapper = new MapperGeneric<>();
                UserInfoResponse.UserAddress userAddress = userAddressMapper.ModelmapToDTO(userAddresses,UserInfoResponse.UserAddress.class);
                userAddress.setId(userAddresses.getId());
                listUserAddressResponse.add(userAddress);
            }
            List<String>roleList =new ArrayList<>();
            for(Roles role : user.getRoles()){
                roleList.add(role.getName());
            }
            userInfoResponse.setId(user.getId());
            userInfoResponse.setRoles(roleList);
            userInfoResponse.setUserAddresses(listUserAddressResponse);
            listUserInfoResponse.add(userInfoResponse);
        }
        return listUserInfoResponse;
    }

    @Override
    public List<CourseRespones> listCourse() {
        List<Course> coursesEntityList = courseRepository.findAll();
        List<CourseRespones> coursesResponesList = new ArrayList<>();
        MapperGeneric<Location, CourseRespones.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
        MapperGeneric<Course, CourseRespones> CourseMapper = new MapperGeneric<>();
        MapperGeneric<CourseMediaInfo, CourseRespones.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
        MapperGeneric<CourseLocation,CourseRespones.CourseLocation>CourseLocationMapper = new MapperGeneric<>();
        MapperGeneric<Discount, CourseRespones.DiscountDTO> DiscountDToMapper = new MapperGeneric<>();

        for (Course course : coursesEntityList)
        {
            List<CourseRespones.StudentEnrollment> studentEnrollments = new ArrayList<>();
            List<CourseRespones.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
            List<CourseRespones.CourseLocation> courseLocationsList = new ArrayList<>();
            List<CourseRespones.DiscountDTO> DiscountDTOList = new ArrayList<>();
            CourseRespones courseResponse = CourseMapper.ModelmapToDTO(course, CourseRespones.class);
            courseResponse.setId(course.getId());
            courseResponse.setTeacher(course.getTeacher().getFull_name());
            for (CourseEnrollment enrollment : course.getEnrolledStudents())
            {
                CourseRespones.StudentEnrollment studentEnrollment = new CourseRespones.StudentEnrollment();
                studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                studentEnrollments.add(studentEnrollment);
            }
            List<CourseRespones.DiscountDTO> tempDiscountList = new ArrayList<>();
            for(CourseDiscount courseDiscount : course.getCourseDiscounts()){
                Discount discount = courseDiscount.getDiscount();
                CourseRespones.DiscountDTO discountDTO = DiscountDToMapper.ModelmapToDTO(discount,CourseRespones.DiscountDTO.class);
                discountDTO.setQuantity(courseDiscount.getQuantity());
                discountDTO.setRedemptionDate(courseDiscount.getRedemptionDate());
                discountDTO.setId(discount.getId());
                boolean isAlreadyExists = false;
                for (CourseRespones.DiscountDTO existingDiscountDTO : tempDiscountList) {
                    if (existingDiscountDTO.getId() == discountDTO.getId()) {
                        isAlreadyExists = true;
                        break;
                    }
                }
                if (!isAlreadyExists) {
                    DiscountDTOList.add(discountDTO);
                    tempDiscountList.add(discountDTO);
                }
            }
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos())
            {
                if(courseMediaInfo.getCourse().equals(course)){
                    CourseRespones.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo,CourseRespones.CourseMediaInfo.class);
                    courseInfoMedia.setId(courseMediaInfo.getId());
                    courseInfoMediaList.add(courseInfoMedia);
                }
            }
            for (CourseLocation courseLocation : course.getCourseLocation())
            {
                CourseRespones.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation,CourseRespones.CourseLocation.class);
                courseLocal.setId(courseLocation.getId());
                if(courseLocation.getLocations()!=null){
                    CourseRespones.CourseLocation.locationResponse location =
                            locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseRespones.CourseLocation.locationResponse.class);
                    courseLocal.setLocationResponse(location);
                }
                courseLocationsList.add(courseLocal);
            }
            courseResponse.setStudentEnrollments(studentEnrollments);
            courseResponse.setCourseMediaInfos(courseInfoMediaList);
            courseResponse.setDiscountDTOS(DiscountDTOList);
            courseResponse.setCourseLocations(courseLocationsList);
            coursesResponesList.add(courseResponse);
        }
        return coursesResponesList;
    }

    @Override
    public List<WorkShopRespone> listWorkshop() {
        List<WorkShopRespone>workshopResponesList = new ArrayList<>();
        return workshopResponesList;
    }
}
