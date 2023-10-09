package com.workshop.model;

import com.workshop.model.courseModel.CourseEnrollment;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="transactions")
public class Transaction extends BaseModel{
    private double amount;
    private String status;
    private Date transactionDate;

    // Quan hệ một nhiều với khóa học đã thanh toán hoặc buổi workshop đã thanh toán
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    // Quan hệ một một với yêu cầu liên quan (đăng ký khóa học, workshop, v.v.)
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
    // Quan hệ một nhiều với người dùng đã thực hiện thanh toán
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_enrollment_id")
    private CourseEnrollment courseEnrollment;

    // Quan hệ nhiều một với phương thức thanh toán
    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;


    @ManyToOne
    @JoinColumn(name = "workshop_enrollmen_id")
    private WorkshopEnrollment workshopEnrollment;

}
