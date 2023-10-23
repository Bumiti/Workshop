package com.workshop.model;

import com.workshop.model.courseModel.CourseDiscount;
import com.workshop.model.userModel.User;
import com.workshop.model.workshopModel.WorkshopDiscount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="discount")
public class Discount extends BaseModel{
    private String name;
    private String description;
    private int valueDiscount ;
    private int remainingUses;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "discount")
    private List<WorkshopDiscount> workshopDiscounts;

    @OneToMany(mappedBy = "discount")
    private List<CourseDiscount> courseDiscounts;

}
