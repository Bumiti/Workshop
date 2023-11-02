package com.workshop.model;

import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.model.workshopModel.Workshop;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="requesttb")
public class Request extends BaseModel{

    @Enumerated(EnumType.STRING)
    private RequestType type;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
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
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // Quan hệ nhiều một với thanh toán (khi yêu cầu đã được duyệt và thanh toán)
    @OneToMany(mappedBy = "request")
    private List<Transaction> transactions;

    public enum RequestStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
    public enum RequestType {
        Register_Course_Online,
        Register_Course_Offline,
//        Register_WorkShop_Online,
//        Register_WorkShop_Offline,
    }
}
