package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Discount;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "course_discounts")
public class CourseDiscount extends BaseModel {
    private Date redemptionDate;
    private int quantity;
    // Quan hệ một nhiều với khóa học
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    // Quan hệ một nhiều với ưu đãi
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
