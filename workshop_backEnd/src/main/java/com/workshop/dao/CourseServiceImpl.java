package com.workshop.dao;

import com.workshop.config.MapperGeneric;
import com.workshop.dto.CourseDTO.*;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.model.*;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.Course.*;
import com.workshop.reposetory.DiscountRepository;
import com.workshop.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseDiscountRepository courseDiscountRepository;
    private final DiscountRepository discountRepository;
    private final CourseMediaInfoRepository courseMediaInfoRepository;
    private final UserServiceImpl userService;
    private final CourseLocationRepository courseLocationRepository;

    boolean isCourse(Long Id) {
        Course course_exit = courseRepository.findCourseById(Id);
        return course_exit != null;
    }
    @Override
    public boolean addCourse(CourseRequest courseRequest) {
        User user = userService.getCurrentUserDetails();
        if (courseRequest != null) {
            //Add Course
            MapperGeneric<Course, CourseRequest> mapperGeneric = new MapperGeneric<>();
            MapperGeneric<CourseMediaInfo, CourseRequest.CourseMediaInfoDTOS> mapperMediaGeneric = new MapperGeneric<>();
            MapperGeneric<CourseLocation, CourseRequest.CourseLocation> mapperLocationGeneric = new MapperGeneric<>();
            List<CourseRequest.CourseMediaInfoDTOS> courseMediaInfoDTOSList = courseRequest.getMediaInfoList();
            List<CourseRequest.DiscountDTO> discountDTOSList = courseRequest.getDiscountDTOS();
            List<CourseRequest.CourseLocation> courseLocationsList = courseRequest.getCourseLocation();
            Course course = mapperGeneric.DTOmapToModel(courseRequest, Course.class);
            course.setTeacher(user);
            courseRepository.save(course);
            //Add Media into Course
            if (courseMediaInfoDTOSList != null) {
                for (CourseRequest.CourseMediaInfoDTOS mediaInfoDTOS : courseMediaInfoDTOSList) {
                    CourseMediaInfo mediaInfo = mapperMediaGeneric.DTOmapToModel(mediaInfoDTOS, CourseMediaInfo.class);
                    mediaInfo.setCourse(course);
                    courseMediaInfoRepository.save(mediaInfo);
                }
            }
            //Add Discount into Course
            if (discountDTOSList != null) {
                for (CourseRequest.DiscountDTO discountDTO : discountDTOSList) {
                    if (discountDTO.getQuantity() > 0) {
                        Discount discount = new Discount();
                        discount.setDescription(discountDTO.getDescription())
                                .setName(discountDTO.getName())
                                .setRemainingUses(discountDTO.getQuantity())
                                .setValueDiscount(discountDTO.getValueDiscount());
                        discountRepository.save(discount);
                        for (int i = 0; i < discountDTO.getQuantity(); i++) {
                            CourseDiscount courseDiscount = new CourseDiscount();
                            UUID randomUUID = UUID.randomUUID();
                            String randomDiscountCode = randomUUID.toString();
                            courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getQuantity())
                                    .setDiscount(discount).setCourse(course);
                            courseDiscountRepository.save(courseDiscount);
                        }
                    }
                }
            }
            //Add Location into Course
            if(courseLocationsList!=null){
                for (CourseRequest.CourseLocation courseLocation : courseLocationsList) {
                    CourseLocation location = mapperLocationGeneric.DTOmapToModel(courseLocation, CourseLocation.class);
                    location.setCourses(course);
                    courseLocationRepository.save(location);
                }
            }
            return true;
        } else {return false;}
    }

    @Override

    public boolean updateCourse(Long id, CourseUpdateRequest courseRequest) {
        User user = userService.getCurrentUserDetails();
        Course courseExit = courseRepository.findCourseById(id);
        try {
            MapperGeneric<Course, CourseUpdateRequest> mapperGeneric = new MapperGeneric<>();
            MapperGeneric<CourseMediaInfo, CourseUpdateRequest.CourseMediaInfoDTOS> mapperMediaGeneric = new MapperGeneric<>();
            MapperGeneric<CourseLocation, CourseUpdateRequest.CourseLocation> mapperLocationGeneric = new MapperGeneric<>();
            MapperGeneric<Discount, CourseUpdateRequest.DiscountDTO> mapperDiscountGeneric = new MapperGeneric<>();
            List<CourseUpdateRequest.CourseMediaInfoDTOS> courseMediaInfoDTOSList = courseRequest.getMediaInfoList();
            List<CourseUpdateRequest.DiscountDTO> discountDTOSList = courseRequest.getDiscountDTOS();
            List<CourseUpdateRequest.CourseLocation> courseLocationsList = courseRequest.getCourseLocation();
            Course course = mapperGeneric.DTOmapToModel(courseRequest, Course.class);
            course.setTeacher(user);
            courseRepository.updateCourse(id, course);
            if (courseMediaInfoDTOSList != null) {
                for (CourseUpdateRequest.CourseMediaInfoDTOS mediaInfoDTOS : courseMediaInfoDTOSList) {
                    CourseMediaInfo mediaInfo = mapperMediaGeneric.DTOmapToModel(mediaInfoDTOS, CourseMediaInfo.class);
                    Long cMda_id = mediaInfoDTOS.getCourseMedia_id();
                    if (cMda_id > 0) {
                        courseMediaInfoRepository.updateCourse(cMda_id, mediaInfo);
                    } else {
                        mediaInfo.setCourse(courseExit);
                        courseMediaInfoRepository.save(mediaInfo);
                    }
                }
            }
            if (courseLocationsList != null) {
                for (CourseUpdateRequest.CourseLocation courseLocation : courseLocationsList) {
                    CourseLocation location = mapperLocationGeneric.DTOmapToModel(courseLocation, CourseLocation.class);
                    Long cLct_id = courseLocation.getCourseLocation_id();
                    location.setCourses(courseExit);
                    if (cLct_id > 0) {
                        courseLocationRepository.updateCourseLocation(cLct_id, location);
                    } else {
                        location.setCourses(courseExit);
                        courseLocationRepository.save(location);
                    }
                }
            }
            if (discountDTOSList != null) {
                for (CourseUpdateRequest.DiscountDTO discountDTO : discountDTOSList) {
                    Discount existingDiscount = discountRepository.findById(discountDTO.getCourseDiscount_id()).orElse(null);
                    Discount discount = mapperDiscountGeneric.DTOmapToModel(discountDTO, Discount.class);
                    if (existingDiscount != null) {
                        Long dCount_id = discountDTO.getCourseDiscount_id();
                        discount.setRemainingUses(discountDTO.getQuantity());
                        discountRepository.updateDiscount(dCount_id, discount);
                        if (existingDiscount.getRemainingUses() > 0) {
                            int remainingUsesInDatabase = existingDiscount.getRemainingUses();
                            int newQuantity = discountDTO.getQuantity();
                            int courseDiscountAdjustment = newQuantity - remainingUsesInDatabase;
                            if (courseDiscountAdjustment > 0) {
                                for (int i = 0; i < courseDiscountAdjustment; i++) {
                                    CourseDiscount courseDiscount = new CourseDiscount();
                                    UUID randomUUID = UUID.randomUUID();
                                    String randomDiscountCode = randomUUID.toString();
                                    courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(newQuantity)
                                            .setDiscount(existingDiscount).setCourse(course);
                                    courseDiscountRepository.save(courseDiscount);
                                }
                            } else {
                                for (int i = 0; i < -courseDiscountAdjustment; i++) {
//                                    courseDiscountRepository.deleteCourseDiscounts(existingDiscount.getId(), newQuantity);
                                    courseDiscountRepository.deleteCourseDiscountByDiscount(existingDiscount);
                                }
                            }
                        }
                    } else {
                        discount.setRemainingUses(discountDTO.getQuantity());
                        discountRepository.save(discount);
                        for (int i = 0; i < discountDTO.getQuantity(); i++) {

                            CourseDiscount courseDiscount = new CourseDiscount();
                            UUID randomUUID = UUID.randomUUID();
                            String randomDiscountCode = randomUUID.toString();
                            courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getQuantity())
                                    .setDiscount(discount).setCourse(courseExit);

                            courseDiscountRepository.save(courseDiscount);
                        }
                    }
                }
            }
            return true;
        } catch (Exception exception) {
            System.out.println("Error: " + exception.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCourse(Long id) {
        Course course = courseRepository.findCourseById(id);
        if (course.getName() != null) {
            courseRepository.delete(course);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean settingStatusCourse(Long id) {
        try {
            int result = courseRepository.chanceStatusCourseById(id);
            return result > 0;
        } catch (Exception exception) {
            throw new RuntimeException("Error: " + exception);
        }
    }

    @Override
    public List<UserInfoResponse> listStudentByCourse(Long id) {
        try {
            Course course = courseRepository.findCourseById(id);
            if (course != null) {
                List<UserInfoResponse> userInfoResponse = new ArrayList<>();
                List<User> users = courseRepository.listUserInCourse(id);
                MapperGeneric<User, UserInfoResponse> mapper = new MapperGeneric<>();
                for (User user : users) {
                    UserInfoResponse userResponse = mapper.ModelmapToDTO(user, UserInfoResponse.class);
                    userInfoResponse.add(userResponse);
                }
                return userInfoResponse;
            } else {
                return null;
            }
        } catch (RuntimeException runtimeException) {
            System.out.println("Error: " + runtimeException.getMessage());
            return null;
        }
    }

    @Override
    public List<CourseRespones> listCoursebyTeacher() {
        try {
            User teacher = userService.getCurrentUserDetails();
            List<Course> coursesEntityList = courseRepository.listCoursebyTeacherId(teacher.getId());
            List<CourseRespones> coursesResponesList = new ArrayList<>();
            User use = userService.getCurrentUserDetails();
            MapperGeneric<Location, CourseRespones.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
            MapperGeneric<Course, CourseRespones> CourseMapper = new MapperGeneric<>();
            MapperGeneric<CourseMediaInfo, CourseRespones.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
            MapperGeneric<CourseLocation, CourseRespones.CourseLocation> CourseLocationMapper = new MapperGeneric<>();

            for (Course course : coursesEntityList) {
                List<CourseRespones.StudentEnrollment> studentEnrollments = new ArrayList<>();
                List<CourseRespones.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
                List<CourseRespones.CourseLocation> courseLocationsList = new ArrayList<>();
                CourseRespones courseResponse = CourseMapper.ModelmapToDTO(course, CourseRespones.class);
                courseResponse.setId(course.getId());
                courseResponse.setTeacher(use.getFull_name());
                for (CourseEnrollment enrollment : course.getEnrolledStudents()) {
                    CourseRespones.StudentEnrollment studentEnrollment = new CourseRespones.StudentEnrollment();
                    studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                    studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                    studentEnrollments.add(studentEnrollment);
                }
                courseResponse.setStudentEnrollments(studentEnrollments);
                for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()) {
                    if (courseMediaInfo.getCourse().equals(course)) {
                        CourseRespones.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo, CourseRespones.CourseMediaInfo.class);
                        courseInfoMedia.setId(courseMediaInfo.getId());
                        courseInfoMediaList.add(courseInfoMedia);
                    }
                }
                courseResponse.setCourseMediaInfos(courseInfoMediaList);
                for (CourseLocation courseLocation : course.getCourseLocation()) {
                    CourseRespones.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation, CourseRespones.CourseLocation.class);
                    courseLocal.setId(courseLocation.getId());
                    if (courseLocation.getLocations() != null) {
                        CourseRespones.CourseLocation.locationResponse location =
                                locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseRespones.CourseLocation.locationResponse.class);
                        courseLocal.setLocationResponse(location);
                    }
                    courseLocationsList.add(courseLocal);
                }
                courseResponse.setCourseLocations(courseLocationsList);
                coursesResponesList.add(courseResponse);
            }
            return coursesResponesList;
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }
    }

    @Override
    public boolean AddEnrolledStudentsToCourseById(Long Course_id, List<Long> studentIds) {
        try {
            boolean result = isCourse(Course_id);
            if (result) {
                courseRepository.addStudentsToCourse(Course_id, studentIds);
                return true;
            } else {
                return false;
            }
        } catch (RuntimeException runtimeException) {
            System.out.println("Error: " + runtimeException.getMessage());
            return false;
        }
    }

    @Override
    public List<CourseRespones> listCourseEnable() {
        List<Course> coursesEntityList = courseRepository.listCoursePublic();
        List<CourseRespones> coursesResponesList = new ArrayList<>();

        MapperGeneric<Location, CourseRespones.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
        MapperGeneric<Course, CourseRespones> CourseMapper = new MapperGeneric<>();
        MapperGeneric<CourseMediaInfo, CourseRespones.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
        MapperGeneric<CourseLocation,CourseRespones.CourseLocation>CourseLocationMapper = new MapperGeneric<>();

        for (Course course : coursesEntityList)
        {
            List<CourseRespones.StudentEnrollment> studentEnrollments = new ArrayList<>();
            List<CourseRespones.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
            List<CourseRespones.CourseLocation> courseLocationsList = new ArrayList<>();
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
            courseResponse.setStudentEnrollments(studentEnrollments);
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos())
            {
                if(courseMediaInfo.getCourse().equals(course)){
                    CourseRespones.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo,CourseRespones.CourseMediaInfo.class);
                    courseInfoMedia.setId(courseMediaInfo.getId());
                    courseInfoMediaList.add(courseInfoMedia);
                }
            }
            courseResponse.setCourseMediaInfos(courseInfoMediaList);
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
            courseResponse.setCourseLocations(courseLocationsList);
            coursesResponesList.add(courseResponse);
        }
        return coursesResponesList;
    }

}
