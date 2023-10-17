package com.workshop.model.workshopModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Enrollment;
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
@Table(name="workshop_enrollment")
public class WorkshopEnrollment extends BaseModel {
    private Date enrollmentDate;
    // Quan hệ một nhiều với học sinh đã đăng ký
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User enrolledStudent;

    // Quan hệ nhiều một với buổi workshop
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    // Quan hệ một nhiều với Transaction
    @OneToMany(mappedBy = "workshopEnrollment")
    private List<Transaction> payments;

    // Quan hệ một nhiều với thanh toán
    @OneToMany(mappedBy = "workshop_enrollment")
    private List<Enrollment> enrollments;
}
