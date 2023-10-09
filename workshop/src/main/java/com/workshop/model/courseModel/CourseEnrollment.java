package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;

import com.workshop.model.Transaction;
import com.workshop.model.userModel.User;
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
@Table(name = "course_enrollments")
public class CourseEnrollment extends BaseModel {
    private Date enrollmentDate;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User enrolledStudent;

    // Quan hệ nhiều một với khóa học
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Quan hệ một nhiều với thanh toán
    @OneToMany(mappedBy = "courseEnrollment")
    private List<Transaction> transactions;
}
