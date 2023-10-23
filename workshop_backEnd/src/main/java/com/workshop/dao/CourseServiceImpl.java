package com.workshop.dao;

import com.workshop.config.MapperGeneric;
import com.workshop.dto.CourseRequest;
import com.workshop.dto.CourseRespones;
import com.workshop.dto.UserInforRespone;
import com.workshop.model.Discount;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.Course.CourseMediaInfoRepository;
import com.workshop.reposetory.Course.CourseRepository;
import com.workshop.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMediaInfoRepository courseMediaInfoRepository;
    @Autowired
    private UserServiceImpl userService;

    boolean isCourse(Long Id){
        Course course_exit = courseRepository.findCourseById(Id);
        return course_exit != null;
    }
    @Override
    public boolean addCourse(CourseRequest courseRequest) {
        User user = userService.getCurrentUserDetails();

        if(courseRequest !=null){
            MapperGeneric<Course,CourseRequest> mapperGeneric = new MapperGeneric<>();
            MapperGeneric<CourseMediaInfo,CourseRequest.CourseMediaInfoDTOS> mapperMediaGeneric = new MapperGeneric<>();

            Course course =  mapperGeneric.DTOmapToModel(courseRequest,Course.class);

            List<CourseRequest.CourseMediaInfoDTOS> courseMediaInfoDTOSList = courseRequest.getMediaInforList();
            List<CourseRequest.DiscountDTO> discountDTOSList = courseRequest.getDiscountDTOS();
            course.setTeacher(user);
            courseRepository.save(course);

            for (CourseRequest.CourseMediaInfoDTOS mediaInfoDTOS : courseMediaInfoDTOSList) {
                CourseMediaInfo mediaInfo = mapperMediaGeneric.DTOmapToModel(mediaInfoDTOS, CourseMediaInfo.class);
                mediaInfo.setCourse(course);
                courseMediaInfoRepository.save(mediaInfo);
            }
            for(CourseRequest.DiscountDTO discountDTO :discountDTOSList){
                UUID randomUUID = UUID.randomUUID();
                String randomDiscountCode = randomUUID.toString();
                CourseDiscount courseDiscount = new CourseDiscount();
                Discount discount = new Discount();
                discount.setCode(randomDiscountCode)
                        .setDescription(discountDTO.getDescription()).setName(discountDTO.getName()).setRemainingUses(discountDTO.getRemainingUses());
                courseDiscount.setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getQuantity())
                        .setDiscount(discount);
            }

            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean updateCourse(Long id, CourseRequest courseRequest)
    {
        try{
            Course course = new Course();
            course.setPrice(courseRequest.getPrice())
                .setName(courseRequest.getName())
                .setPrice(courseRequest.getPrice())
                .setStartDate(courseRequest.getStartDate())
                .setEndDate(courseRequest.getEndDate())
                .setStudent_count(courseRequest.getStudent_count());
               courseRepository.updateCourse(id,course);
            return true;
        }catch (Exception exception){
            System.out.println("Error: " + exception.getMessage());
            return false;
        }
    }
    @Override
    public boolean deleteCourse(Long id) {
        Course course = courseRepository.findCourseById(id);
        if(course.getName()!=null){
            courseRepository.delete(course);
            return true;
        }else{
            return false;
        }
    }
    @Override
    public boolean settingStatusCourse(Long id) {
        try {
            int result = courseRepository.chanceStatusCourseById(id);
            return result > 0;
        }
        catch (Exception exception) {
            throw new RuntimeException("Error: " + exception);
        }
    }

    @Override
    public List<UserInforRespone> listStudentByCourse(Long id) {
        try{
            Course course = courseRepository.findCourseById(id);
            if(course !=null){
                List<UserInforRespone> userInforRespone = new ArrayList<>();
                List<User> users = courseRepository.listUserInCourse(id);
                MapperGeneric<User, UserInforRespone> mapper = new MapperGeneric<>();
                for (User user : users) {
                    UserInforRespone userResponse = mapper.ModelmapToDTO(user, UserInforRespone.class);
                    userInforRespone.add(userResponse);
                }
                return userInforRespone;
            }else{
                return null;
            }
        }catch (RuntimeException  runtimeException){
            System.out.println("Error: " + runtimeException.getMessage());
            return null;
        }
    }

    @Override
    public List<CourseRespones> listCourse() {
        User teacher = userService.getCurrentUserDetails();
        List<Course> coursesEntityList = courseRepository.listCoursebyTeacherId(teacher.getId());
        List<CourseRespones> coursesResponesList = new ArrayList<>();
        List<CourseRespones.StudentEnrollment> studentEnrollments = new ArrayList<>();
        List<CourseRespones.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
        MapperGeneric<Course, CourseRespones> CourseMapper = new MapperGeneric<>();
        MapperGeneric<CourseMediaInfo, CourseRespones.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
        for (Course course : coursesEntityList) {
            CourseRespones courseResponse = CourseMapper.ModelmapToDTO(course, CourseRespones.class);
            for (CourseEnrollment enrollment : course.getEnrolledStudents())
            {
                CourseRespones.StudentEnrollment studentEnrollment = new CourseRespones.StudentEnrollment();
                studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                studentEnrollments.add(studentEnrollment);
            }
            courseResponse.setStudentEnrollments(studentEnrollments);
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos())
            {
                CourseRespones.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo,CourseRespones.CourseMediaInfo.class);
                courseInfoMediaList.add(courseInfoMedia);
            }
            courseResponse.setCourseMediaInfos(courseInfoMediaList);

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

            coursesResponesList.add(courseResponse);
        }
        return coursesResponesList;
    }

    @Override
    public boolean AddEnrolledStudentsToCourseById(Long Course_id, List<Long> studentIds) {
        try {
            boolean result = isCourse(Course_id);
            if(result){
                courseRepository.addStudentsToCourse(Course_id,studentIds);
                return true;
            }else{
                return false;
            }
        }catch (RuntimeException  runtimeException) {
            System.out.println("Error: " + runtimeException.getMessage());
            return false;
        }
    }

}
