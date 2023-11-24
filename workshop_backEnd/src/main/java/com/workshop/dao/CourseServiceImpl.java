package com.workshop.dao;

import com.workshop.config.MapperGeneric;
import com.workshop.dto.CourseDTO.*;
import com.workshop.dto.useDTO.UserInfoResponse;
import com.workshop.model.*;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.User;
import com.workshop.repositories.Course.*;
import com.workshop.repositories.DiscountRepository;
import com.workshop.repositories.LocationRepository;
import com.workshop.repositories.User.UserRepository;
import com.workshop.service.CourseService;
import com.workshop.service.UserService;
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
    private final UserService userService;
    private final UserRepository userRepository;
    private final CourseLocationRepository courseLocationRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final LocationRepository locationRepository;


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
                            courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getValueDiscount())
                                    .setDiscount(discount).setCourse(course);
                            courseDiscountRepository.save(courseDiscount);
                        }
                    }
                }
            }
            //Add Location into Course
            if (courseLocationsList != null) {
                for (CourseRequest.CourseLocation courseLocation : courseLocationsList) {
                    CourseLocation location = mapperLocationGeneric.DTOmapToModel(courseLocation, CourseLocation.class);
                    location.setCourses(course);
                    courseLocationRepository.save(location);
                }
            }
            return true;
        } else {
            return false;
        }
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
                    Long cMda_id = mediaInfoDTOS.getId();
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
                    Long cLct_id = courseLocation.getId();
                    location.setCourses(courseExit);
                    if (cLct_id > 0) {
                        courseLocationRepository.updateCourseLocation(cLct_id, location);
                    } else {
                        location.setCourses(courseExit);
                        courseLocationRepository.save(location);
                    }
                }
            }
//            if (discountDTOSList != null) {
//                for (CourseUpdateRequest.DiscountDTO discountDTO : discountDTOSList) {
//                    Discount existingDiscount = discountRepository.findById(discountDTO.getId()).orElse(null);
//                    Discount discount = mapperDiscountGeneric.DTOmapToModel(discountDTO, Discount.class);
//                    if (existingDiscount != null) {
//                        Long dCount_id = discountDTO.getId();
//                        discount.setRemainingUses(discountDTO.getQuantity());
//                        discountRepository.updateDiscount(dCount_id, discount);
//                        if (existingDiscount.getRemainingUses() > 0) {
//                            int remainingUsesInDatabase = existingDiscount.getRemainingUses();
//                            int newQuantity = discountDTO.getQuantity();
//                            int courseDiscountAdjustment = newQuantity - remainingUsesInDatabase;
//                            if (courseDiscountAdjustment > 0) {
//                                for (int i = 0; i < courseDiscountAdjustment; i++) {
//                                    CourseDiscount courseDiscount = new CourseDiscount();
//                                    UUID randomUUID = UUID.randomUUID();
//                                    String randomDiscountCode = randomUUID.toString();
//                                    courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getValueDiscount())
//                                            .setDiscount(existingDiscount).setCourse(course);
//                                    courseDiscountRepository.save(courseDiscount);
//                                }
//                            } else {
//                                for (int i = 0; i < -courseDiscountAdjustment; i++) {
//                                    courseDiscountRepository.deleteCourseDiscountByDiscount(existingDiscount);
//                                }
//                            }
//                        }
//                    } else {
//                        discount.setRemainingUses(discountDTO.getQuantity());
//                        discountRepository.save(discount);
//                        for (int i = 0; i < discountDTO.getQuantity(); i++) {
//                        CourseDiscount courseDiscount = new CourseDiscount();
//                        UUID randomUUID = UUID.randomUUID();
//                        String randomDiscountCode = randomUUID.toString();
//                        courseDiscount.setCode(randomDiscountCode).setRedemptionDate(discountDTO.getRedemptionDate()).setQuantity(discountDTO.getValueDiscount())
//                                .setDiscount(discount).setCourse(courseExit);
//                        courseDiscountRepository.save(courseDiscount);
//
//                        }
//                    }
//                }
//            }
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
    public List<CourseResponses> listCourseTeacher() {
        try {
            User teacher = userService.getCurrentUserDetails();
            User use = userService.getCurrentUserDetails();
            List<Course> coursesEntityList = courseRepository.listCoursebyTeacherId(teacher.getId());
            List<CourseResponses> coursesResponesList = new ArrayList<>();

            MapperGeneric<Location, CourseResponses.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
            MapperGeneric<Course, CourseResponses> CourseMapper = new MapperGeneric<>();
            MapperGeneric<CourseMediaInfo, CourseResponses.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
            MapperGeneric<CourseLocation, CourseResponses.CourseLocation> CourseLocationMapper = new MapperGeneric<>();
            MapperGeneric<Discount, CourseResponses.DiscountDTO> DiscountDToMapper = new MapperGeneric<>();
            for (Course course : coursesEntityList) {
                List<CourseResponses.StudentEnrollment> studentEnrollments = new ArrayList<>();
                List<CourseResponses.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
                List<CourseResponses.CourseLocation> courseLocationsList = new ArrayList<>();
                List<CourseResponses.DiscountDTO> DiscountDTOList = new ArrayList<>();
                CourseResponses courseResponse = CourseMapper.ModelmapToDTO(course, CourseResponses.class);
                courseResponse.setId(course.getId());
                courseResponse.setTeacher(use.getFull_name());
                for (CourseEnrollment enrollment : course.getEnrolledStudents()) {
                    CourseResponses.StudentEnrollment studentEnrollment = new CourseResponses.StudentEnrollment();
                    studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                    studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                    studentEnrollments.add(studentEnrollment);
                }
                List<CourseResponses.DiscountDTO> tempDiscountList = new ArrayList<>();
                for (CourseDiscount courseDiscount : course.getCourseDiscounts()) {
                    Discount discount = courseDiscount.getDiscount();
                    CourseResponses.DiscountDTO discountDTO = DiscountDToMapper.ModelmapToDTO(discount, CourseResponses.DiscountDTO.class);
                    discountDTO.setQuantity(courseDiscount.getQuantity());
                    discountDTO.setRedemptionDate(courseDiscount.getRedemptionDate());
                    discountDTO.setId(discount.getId());
                    boolean isAlreadyExists = false;
                    for (CourseResponses.DiscountDTO existingDiscountDTO : tempDiscountList) {
                        if (Objects.equals(existingDiscountDTO.getId(), discountDTO.getId())) {
                            isAlreadyExists = true;
                            break;
                        }
                    }
                    if (!isAlreadyExists) {
                        DiscountDTOList.add(discountDTO);
                        tempDiscountList.add(discountDTO);
                    }
                }
                for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()) {
                    if (courseMediaInfo.getCourse().equals(course)) {
                        CourseResponses.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo, CourseResponses.CourseMediaInfo.class);
                        courseInfoMedia.setId(courseMediaInfo.getId());
                        courseInfoMediaList.add(courseInfoMedia);
                    }
                }
                for (CourseLocation courseLocation : course.getCourseLocation()) {
                    CourseResponses.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation, CourseResponses.CourseLocation.class);
                    courseLocal.setId(courseLocation.getId());
                    if (courseLocation.getLocations() != null) {
                        CourseResponses.CourseLocation.locationResponse location =
                                locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseResponses.CourseLocation.locationResponse.class);
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
    public List<CourseResponses> listCourseEnable() {
        List<Course> coursesEntityList = courseRepository.listCoursePublic();
        List<CourseResponses> coursesResponesList = new ArrayList<>();
        MapperGeneric<Location, CourseResponses.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
        MapperGeneric<Course, CourseResponses> CourseMapper = new MapperGeneric<>();
        MapperGeneric<CourseMediaInfo, CourseResponses.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
        MapperGeneric<CourseLocation, CourseResponses.CourseLocation> CourseLocationMapper = new MapperGeneric<>();
        MapperGeneric<Discount, CourseResponses.DiscountDTO> DiscountDToMapper = new MapperGeneric<>();
        for (Course course : coursesEntityList) {
            List<CourseResponses.StudentEnrollment> studentEnrollments = new ArrayList<>();
            List<CourseResponses.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
            List<CourseResponses.CourseLocation> courseLocationsList = new ArrayList<>();
            List<CourseResponses.DiscountDTO> DiscountDTOList = new ArrayList<>();
            CourseResponses courseResponse = CourseMapper.ModelmapToDTO(course, CourseResponses.class);
            courseResponse.setId(course.getId());
            courseResponse.setTeacher(course.getTeacher().getFull_name());
            for (CourseEnrollment enrollment : course.getEnrolledStudents()) {
                CourseResponses.StudentEnrollment studentEnrollment = new CourseResponses.StudentEnrollment();
                studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                studentEnrollments.add(studentEnrollment);
            }
            List<CourseResponses.DiscountDTO> tempDiscountList = new ArrayList<>();
            for (CourseDiscount courseDiscount : course.getCourseDiscounts()) {
                Discount discount = courseDiscount.getDiscount();
                CourseResponses.DiscountDTO discountDTO = DiscountDToMapper.ModelmapToDTO(discount, CourseResponses.DiscountDTO.class);
                discountDTO.setQuantity(courseDiscount.getQuantity());
                discountDTO.setRedemptionDate(courseDiscount.getRedemptionDate());
                discountDTO.setId(discount.getId());
                boolean isAlreadyExists = false;
                for (CourseResponses.DiscountDTO existingDiscountDTO : tempDiscountList) {
                    if (Objects.equals(existingDiscountDTO.getId(), discountDTO.getId())) {
                        isAlreadyExists = true;
                        break;
                    }
                }
                if (!isAlreadyExists) {
                    DiscountDTOList.add(discountDTO);
                    tempDiscountList.add(discountDTO);
                }
            }
            for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()) {
                if (courseMediaInfo.getCourse().equals(course)) {
                    CourseResponses.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo, CourseResponses.CourseMediaInfo.class);
                    courseInfoMedia.setId(courseMediaInfo.getId());
                    courseInfoMediaList.add(courseInfoMedia);
                }
            }
            for (CourseLocation courseLocation : course.getCourseLocation()) {
                CourseResponses.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation, CourseResponses.CourseLocation.class);
                courseLocal.setId(courseLocation.getId());
                if (courseLocation.getLocations() != null) {
                    CourseResponses.CourseLocation.locationResponse location =
                            locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseResponses.CourseLocation.locationResponse.class);
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
    public int checkCodeDiscount(String discountCode) {
        try{
            if(discountCode!=null){
                int value = 0;
             CourseDiscount courseDiscount = courseDiscountRepository.findByCode(discountCode);
                if (courseDiscount != null) {
                    value = courseDiscount.getQuantity();
                }
                return value;
            }else{
                return 0;
            }
        }catch (Exception e){
            return 0;
        }
    }
    @Override
    public CourseResponses courseById(Long id) {
        try {
            boolean result = isCourse(id);
            Course course = courseRepository.CoursePublic(id);
            if (result && course!=null)
            {

                MapperGeneric<Location, CourseResponses.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
                MapperGeneric<Course, CourseResponses> CourseMapper = new MapperGeneric<>();
                MapperGeneric<CourseMediaInfo, CourseResponses.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
                MapperGeneric<CourseLocation, CourseResponses.CourseLocation>CourseLocationMapper = new MapperGeneric<>();
                MapperGeneric<Discount, CourseResponses.DiscountDTO> DiscountDToMapper = new MapperGeneric<>();
                List<CourseResponses.StudentEnrollment> studentEnrollments = new ArrayList<>();
                List<CourseResponses.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
                List<CourseResponses.CourseLocation> courseLocationsList = new ArrayList<>();
                List<CourseResponses.DiscountDTO> DiscountDTOList = new ArrayList<>();
                CourseResponses courseResponse = CourseMapper.ModelmapToDTO(course, CourseResponses.class);
                courseResponse.setId(course.getId());
                courseResponse.setTeacher(course.getTeacher().getFull_name());
                for (CourseEnrollment enrollment : course.getEnrolledStudents())
                {
                    CourseResponses.StudentEnrollment studentEnrollment = new CourseResponses.StudentEnrollment();
                    studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                    studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                    studentEnrollments.add(studentEnrollment);
                }
                List<CourseResponses.DiscountDTO> tempDiscountList = new ArrayList<>();
                for(CourseDiscount courseDiscount : course.getCourseDiscounts()){
                    Discount discount = courseDiscount.getDiscount();
                    CourseResponses.DiscountDTO discountDTO = DiscountDToMapper.ModelmapToDTO(discount, CourseResponses.DiscountDTO.class);
                    discountDTO.setQuantity(courseDiscount.getQuantity());
                    discountDTO.setRedemptionDate(courseDiscount.getRedemptionDate());
                    discountDTO.setId(discount.getId());
                    boolean isAlreadyExists = false;
                    for (CourseResponses.DiscountDTO existingDiscountDTO : tempDiscountList) {
                        if (Objects.equals(existingDiscountDTO.getId(), discountDTO.getId())) {
                            isAlreadyExists = true;
                            break;
                        }
                    }
                    if (!isAlreadyExists) {
                        DiscountDTOList.add(discountDTO);
                        tempDiscountList.add(discountDTO);
                    }
                }
                for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()){
                    if(courseMediaInfo.getCourse().equals(course)){
                        CourseResponses.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo, CourseResponses.CourseMediaInfo.class);
                        courseInfoMedia.setId(courseMediaInfo.getId());
                        courseInfoMediaList.add(courseInfoMedia);
                    }
                }
                for (CourseLocation courseLocation : course.getCourseLocation()){
                    CourseResponses.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation, CourseResponses.CourseLocation.class);
                    courseLocal.setId(courseLocation.getId());
                    if(courseLocation.getLocations()!=null){
                        CourseResponses.CourseLocation.locationResponse location =
                                locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseResponses.CourseLocation.locationResponse.class);
                        courseLocal.setLocationResponse(location);
                    }courseLocationsList.add(courseLocal);
                }
                courseResponse.setStudentEnrollments(studentEnrollments);
                courseResponse.setCourseMediaInfos(courseInfoMediaList);
                courseResponse.setDiscountDTOS(DiscountDTOList);
                courseResponse.setCourseLocations(courseLocationsList);
               return courseResponse ;
            } else {
                return null;
            }
        } catch (RuntimeException runtimeException) {
            System.out.println("Error: " + runtimeException.getMessage());
            return null;
        }
    }

    @Override
    public boolean checkUserInCourse(String email, Long course_id) {
       try{
           boolean result = isCourse(course_id);
           User user = userRepository.getUserEditByMail(email);
           Long user_id = user.getId();
           if(result){
               return courseEnrollmentRepository.checkUserInCourse(user_id,course_id);
           }
           return false;
       }catch (Exception e){
           return false;
       }
    }
    @Override
    public boolean UpdateLocationToLocationCourse(Long CourseLocation_id, Long Location_Id) {
        try{
            CourseLocation courseLocation = courseLocationRepository.findCourseLocationById(CourseLocation_id);
            Location location = locationRepository.findLocationById(Location_Id);
            if(courseLocation!=null && location!=null){
                courseLocationRepository.updateLocationToLocationCourse(CourseLocation_id,Location_Id);
                return true;
            }else{
                return false;
            }
        }catch (Exception exception){
            throw  exception;
        }
    }

    public List<CourseResponses> listCourseTeacherById(Long courseId) {
        try {
            Course courseEntity = courseRepository.findCourseById(courseId);
            if (courseEntity != null) {
                User teacher = userService.getCurrentUserDetails();
                User use = userService.getCurrentUserDetails();
                List<Course> coursesEntityList = courseRepository.listCoursebyTeacherId(teacher.getId());
                List<CourseResponses> coursesResponesList = new ArrayList<>();

                MapperGeneric<Location, CourseResponses.CourseLocation.locationResponse> locationMapper = new MapperGeneric<>();
                MapperGeneric<Course, CourseResponses> CourseMapper = new MapperGeneric<>();
                MapperGeneric<CourseMediaInfo, CourseResponses.CourseMediaInfo> CourseMediaMapper = new MapperGeneric<>();
                MapperGeneric<CourseLocation, CourseResponses.CourseLocation> CourseLocationMapper = new MapperGeneric<>();
                MapperGeneric<Discount, CourseResponses.DiscountDTO> DiscountDToMapper = new MapperGeneric<>();

                for (Course course : coursesEntityList) {
                    if (course.getId().equals(courseId)) {
                        List<CourseResponses.StudentEnrollment> studentEnrollments = new ArrayList<>();
                        List<CourseResponses.CourseMediaInfo> courseInfoMediaList = new ArrayList<>();
                        List<CourseResponses.CourseLocation> courseLocationsList = new ArrayList<>();
                        List<CourseResponses.DiscountDTO> DiscountDTOList = new ArrayList<>();

                        CourseResponses courseResponse = CourseMapper.ModelmapToDTO(course, CourseResponses.class);
                        courseResponse.setId(course.getId());
                        courseResponse.setTeacher(use.getFull_name());
                        for (CourseEnrollment enrollment : course.getEnrolledStudents()) {
                            CourseResponses.StudentEnrollment studentEnrollment = new CourseResponses.StudentEnrollment();
                            studentEnrollment.setId(enrollment.getEnrolledStudent().getId());
                            studentEnrollment.setName(enrollment.getEnrolledStudent().getUser_name());
                            studentEnrollments.add(studentEnrollment);
                        }
                        List<CourseResponses.DiscountDTO> tempDiscountList = new ArrayList<>();
                        for (CourseDiscount courseDiscount : course.getCourseDiscounts()) {
                            Discount discount = courseDiscount.getDiscount();
                            CourseResponses.DiscountDTO discountDTO = DiscountDToMapper.ModelmapToDTO(discount, CourseResponses.DiscountDTO.class);
                            discountDTO.setQuantity(courseDiscount.getQuantity());
                            discountDTO.setRedemptionDate(courseDiscount.getRedemptionDate());
                            discountDTO.setId(discount.getId());
                            boolean isAlreadyExists = false;
                            for (CourseResponses.DiscountDTO existingDiscountDTO : tempDiscountList) {
                                if (Objects.equals(existingDiscountDTO.getId(), discountDTO.getId())) {
                                    isAlreadyExists = true;
                                    break;
                                }
                            }
                            if (!isAlreadyExists) {
                                DiscountDTOList.add(discountDTO);
                                tempDiscountList.add(discountDTO);
                            }
                        }
                        for (CourseMediaInfo courseMediaInfo : course.getCourseOnlineInfos()) {
                            if (courseMediaInfo.getCourse().equals(course)) {
                                CourseResponses.CourseMediaInfo courseInfoMedia = CourseMediaMapper.ModelmapToDTO(courseMediaInfo, CourseResponses.CourseMediaInfo.class);
                                courseInfoMedia.setId(courseMediaInfo.getId());
                                courseInfoMediaList.add(courseInfoMedia);
                            }
                        }
                        for (CourseLocation courseLocation : course.getCourseLocation()) {
                            CourseResponses.CourseLocation courseLocal = CourseLocationMapper.ModelmapToDTO(courseLocation, CourseResponses.CourseLocation.class);
                            courseLocal.setId(courseLocation.getId());
                            if (courseLocation.getLocations() != null) {
                                CourseResponses.CourseLocation.locationResponse location =
                                        locationMapper.ModelmapToDTO(courseLocation.getLocations(), CourseResponses.CourseLocation.locationResponse.class);
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
                }
                return coursesResponesList;
            } else {
                return Collections.emptyList();
            }
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }
    }
}
