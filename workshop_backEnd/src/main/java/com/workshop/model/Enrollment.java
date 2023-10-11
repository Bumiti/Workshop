package com.workshop.model;

import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.model.workshopModel.Workshop;
import com.workshop.model.workshopModel.WorkshopEnrollment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="enrollment")
public class Enrollment extends BaseModel {
    private Date enrollmentDate;
    // Quan hệ nhiều một với người dùng đã đăng ký
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User enrolledStudent;
    // Quan hệ một nhiều với khóa học (nếu là đăng ký khóa học)
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Quan hệ một nhiều với buổi workshop (nếu là đăng ký workshop)
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    // Quan hệ một nhiều với buổi workshop (nếu là đăng ký workshop)
    @ManyToOne
    @JoinColumn(name = "workshop_enrollment_id")
    private WorkshopEnrollment workshop_enrollment;

    // Quan hệ một nhiều với thanh toán (khi đăng ký đã được thanh toán)
    @OneToMany(mappedBy = "enrollment")
    private List<Transaction> transactions;
}
