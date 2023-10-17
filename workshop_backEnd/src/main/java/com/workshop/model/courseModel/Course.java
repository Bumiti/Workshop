package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Discount;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    private int student_count;
    private boolean isPublic = false;
    private String type;

    // Quan hệ nhiều một với giáo viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @OneToMany(mappedBy = "courses")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CourseEnrollment> enrolledStudents;


    @OneToMany(mappedBy = "course")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CourseMediaInfo> courseOnlineInfos;

    @OneToMany(mappedBy = "courses")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CourseLocation> CourseLocation;

    // Quan hệ nhiều nhiều với ưu đãi
    @ManyToMany(mappedBy = "courses")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Discount> discounts;


}
