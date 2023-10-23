package com.workshop.dao;


import com.workshop.dto.*;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.*;
import com.workshop.reposetory.Course.CourseRepository;
import com.workshop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private WorkShopRepository workShopRepository;

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
    public List<UserInforRespone> listAccountByRole(String role) {
        List<UserInforRespone> listUserInforRespone = new ArrayList<>();
        List<User> ListUser = userRepository.findUsersByRoleName(role);
        for (User user : ListUser) {
            UserInforRespone userInforRespone = new UserInforRespone();
            userInforRespone.setId(user.getId())
                    .setUser_name(user.getUser_name())
                    .setFull_name(user.getFull_name())
                    .setImage_url(user.getImage_url())
                    .setPhoneNumber(user.getPhoneNumber())
                    .setEnable(user.isEnable());
            listUserInforRespone.add(userInforRespone);

        }
        return listUserInforRespone;
    }

    @Override
    public List<CourseRespones> listCourse() {
        List<Course> courses = courseRepository.findAll();
        List<CourseRespones> CourseList = new ArrayList<>();
        for (Course course : courses) {
            CourseRespones courseResponse = new CourseRespones();
            courseResponse.setId(course.getId()).setName(course.getName())
                    .setDescription(course.getDescription())
                    .setPrice(course.getPrice())
                    .setStartDate(course.getStartDate())
                    .setEndDate(course.getEndDate())
                    .setType(course.getType())
                    .setPublic(course.isPublic())
                    .setTeacher(course.getTeacher().getUser_name())
            ;
            List<CourseRespones.StudentEnrollment> studentEnrollments = new ArrayList<>();
            for (CourseEnrollment enrollment : course.getEnrolledStudents()) {
                CourseRespones.StudentEnrollment studentEnrollment = new CourseRespones.StudentEnrollment();
                studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                studentEnrollments.add(studentEnrollment);
            }
            courseResponse.setStudentEnrollments(studentEnrollments);

            List<CourseRespones.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()) {
                CourseRespones.CourseMediaInfo courseInfoMedia = new CourseRespones.CourseMediaInfo();
                courseInfoMedia.setId(courseMediaInfo.getId());
                courseInfoMedia.setUrlMedia(courseMediaInfo.getUrlMedia());
                courseInfoMedia.setUrlImage(courseMediaInfo.getUrlImage());
            }
            courseResponse.setCourseMediaInfos(courseInfoMediaList);
            List<CourseRespones.CourseLocation> courseLocations = new ArrayList<>();
            for (CourseLocation courseLocation : course.getCourseLocation()) {
                CourseRespones.CourseLocation location = new CourseRespones.CourseLocation();
                location.setId(courseLocation.getId());
                location.setScheduleDate(courseLocation.getScheduleDate());
                location.setName(courseLocation.getLocations().getName());
                location.setAddress(courseLocation.getLocations().getAddress());
                location.setDescription(courseLocation.getLocations().getName());
            }
            courseResponse.setCourseLocations(courseLocations);

            CourseList.add(courseResponse);
        }
        return CourseList;
    }

    @Override
    public List<WorkShopRespone> listWorkshop() {

        List<WorkShopRespone>workshopResponesList = new ArrayList<>();

        return workshopResponesList;
    }
}
