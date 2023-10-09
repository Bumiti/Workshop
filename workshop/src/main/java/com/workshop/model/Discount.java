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
@Table(name="discount")
public class Discount extends BaseModel{
    private String name;
    private String description;
    private int remainingUses;

    // Quan hệ một nhiều với người dùng đã được cấp ưu đãi
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Quan hệ nhiều nhiều với các khóa học
    @ManyToMany
    @JoinTable(
            name = "discount_course",
            joinColumns = @JoinColumn(name = "discount_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
