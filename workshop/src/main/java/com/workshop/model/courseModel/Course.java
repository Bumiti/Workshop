package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Discount;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="course")
@Accessors(chain = true)
public class Course extends BaseModel {
    private String name;
    private String description;
    private double price;
    private Date startDate;
    private Date endDate;
    private boolean isPublic;
    // Quan hệ nhiều một với giáo viên
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @OneToMany(mappedBy = "courses")
    private List<CourseEnrollment> enrolledStudents;

    // Quan hệ nhiều nhiều với ưu đãi
    @ManyToMany(mappedBy = "courses")
    private List<Discount> discounts;


}
