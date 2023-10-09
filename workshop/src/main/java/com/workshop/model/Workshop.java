package com.workshop.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

import com.workshop.model.userModel.User;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="workshop")
public class Workshop extends BaseModel{
    private String name;
    private String description;
    private double price;
    private Date date;
    private Date time;
    private boolean isApproved;

    // Quan hệ nhiều một với giáo viên tổ chức
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    // Quan hệ một nhiều với học sinh đã đăng ký
    @OneToMany(mappedBy = "workshop")
    private List<WorkshopEnrollment> enrolledStudents;

    // Quan hệ một nhiều với địa điểm tổ chức
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
