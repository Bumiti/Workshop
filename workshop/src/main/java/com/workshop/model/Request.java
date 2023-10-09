package com.workshop.model;

import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="request")
public class Request extends BaseModel{
    private String type; // Loại yêu cầu (đăng ký khóa học, đăng ký workshop, đăng ký buổi offline, v.v.)
    private String status; // Trạng thái yêu cầu (đã duyệt, chưa duyệt, v.v.)

    // Quan hệ nhiều một với người dùng tạo yêu cầu
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Quan hệ một một với khóa học (nếu là yêu cầu đăng ký khóa học)
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    // Quan hệ một một với buổi workshop (nếu là yêu cầu đăng ký workshop)
    @OneToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    // Quan hệ một một với địa điểm (nếu là yêu cầu đăng ký buổi offline)
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Quan hệ nhiều một với thanh toán (khi yêu cầu đã được duyệt và thanh toán)
    @OneToMany(mappedBy = "request")
    private List<Transaction> transactions;
}
