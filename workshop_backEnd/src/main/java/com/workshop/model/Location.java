package com.workshop.model;

import com.workshop.model.courseModel.CourseLocation;
import com.workshop.model.workshopModel.Workshop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="location")
public class Location extends BaseModel{
    private String name;
    private String address;
    private String description;

    // Quan hệ một nhiều với buổi workshop có thể tổ chức tại đây
    @OneToMany(mappedBy = "location")
    private List<Workshop> workshops;
    @OneToMany(mappedBy = "location")
    private List<Request> requests;

    @OneToMany(mappedBy = "locations")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<CourseLocation> CourseLocation;
}
