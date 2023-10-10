package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Discount;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="course")
public class Course extends BaseModel {
    private String name;
    private String description;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean isPublic;
    private boolean type;
    // Quan hệ nhiều một với giáo viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @OneToMany(mappedBy = "courses")
    private List<CourseEnrollment> enrolledStudents;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @OneToMany(mappedBy = "course_online")
    private List<CourseOnlineInfo> courseOnlineInfos;
    // Quan hệ một nhiều với học sinh đã đăng ký

    @OneToMany(mappedBy = "course_offline")
    private List<CourseLocation> courseLocations;

    // Quan hệ nhiều nhiều với ưu đãi
    @ManyToMany(mappedBy = "courses")
    private List<Discount> discounts;


}
