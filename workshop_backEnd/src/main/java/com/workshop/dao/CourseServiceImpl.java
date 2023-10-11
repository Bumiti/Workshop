package com.workshop.dao;

import com.workshop.config.MapperGeneric;
import com.workshop.dto.CourseRequest;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.reposetory.CourseRepository;
import com.workshop.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepository;
    @Autowired UserServiceImpl userService;
    @Override
    public boolean AddCourse(CourseRequest courseRequest) {
        if(courseRequest !=null){
            MapperGeneric<Course,CourseRequest> mapperGeneric = new MapperGeneric<>();
            Course course =  mapperGeneric.DTOmapToModel(courseRequest,Course.class);
            User user = userService.getCurrentUserDetails();
            course.setTeacher(user);
            courseRepository.save(course);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean DeleteCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        if(course.getName()!=null){
            courseRepository.delete(course);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean SettingStatusCourse(Long id) {
        Course course = courseRepository.findById(id).orElse(null);
        if(course.getName()!=null ){
            courseRepository.updateById(id,!course.isPublic());
            return true;
        }else{
            return false;
        }
    }

}
