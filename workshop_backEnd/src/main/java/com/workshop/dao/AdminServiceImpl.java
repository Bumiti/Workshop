package com.workshop.dao;

import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.CourseEnrollment;
import com.workshop.model.courseModel.CourseLocation;
import com.workshop.model.courseModel.CourseMediaInfo;
import com.workshop.model.userModel.User;
import com.workshop.model.workshopModel.Workshop;
import com.workshop.reposetory.CourseRepository;
import com.workshop.reposetory.UserRepository;
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
    @Override
    public List<UserInforRespone> ListStudent() {
        List<UserInforRespone>listUserInforRespone =new ArrayList<>();
        List<User> ListUser = userRepository.findUsersByRoleName("USER");
        for(User user :ListUser){
            UserInforRespone userInforRespone = new UserInforRespone();
            userInforRespone.setAddress(user.getAddress())
                    .setUser_name(user.getUser_name())
                    .setFull_name(user.getFull_name())
                    .setImage(user.getImage_url())
                    .setPhoneNumber(user.getPhoneNumber())
                    .setEnable(user.isEnable());
            listUserInforRespone.add(userInforRespone);

        }
        return listUserInforRespone ;
    }

    @Override
    public List<UserInforRespone> ListTeacher() {

        List<UserInforRespone>listUserInforRespone =new ArrayList<>();
        List<User> ListUser = userRepository.findUsersByRoleName("SELLER");
        for(User user :ListUser){
            UserInforRespone userInforRespone = new UserInforRespone();
            userInforRespone.setAddress(user.getAddress())
                    .setUser_name(user.getUser_name())
                    .setFull_name(user.getFull_name())
                    .setImage(user.getImage_url())
                    .setPhoneNumber(user.getPhoneNumber())
                    .setEnable(user.isEnable());
            listUserInforRespone.add(userInforRespone);
        }
        return listUserInforRespone ;

    }

    @Override
    public List<CourseRespones> ListCourse() {
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
            for (CourseEnrollment enrollment : course.getEnrolledStudents())
            {
                CourseRespones.StudentEnrollment studentEnrollment = new CourseRespones.StudentEnrollment();
                studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                studentEnrollments.add(studentEnrollment);
            }
            courseResponse.setStudentEnrollments(studentEnrollments);

            List<CourseRespones.CourseInfoMedia> courseInfoMediaList = new ArrayList<>();
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos())
            {
                CourseRespones.CourseInfoMedia courseInfoMedia = new CourseRespones.CourseInfoMedia();
                courseInfoMedia.setId(courseMediaInfo.getId());
                courseInfoMedia.setUrlMedia(courseMediaInfo.getUrlMedia());
                courseInfoMedia.setUrlImage(courseMediaInfo.getUrlImage());
            }
            courseResponse.setCourseInfoMedia(courseInfoMediaList);
            List<CourseRespones.CourseLocation> courseLocations = new ArrayList<>();
            for (CourseLocation courseLocation : course.getCourseLocation())
            {
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
    public List<Workshop> ListWorkshop() {
        return null;
    }
}
