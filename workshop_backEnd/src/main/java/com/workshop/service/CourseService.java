package com.workshop.service;

import com.workshop.dto.CourseRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseService {
    boolean AddCourse(CourseRequest courseRequest);
    boolean DeleteCourse(Long id);
    boolean SettingStatusCourse(Long id);

}
