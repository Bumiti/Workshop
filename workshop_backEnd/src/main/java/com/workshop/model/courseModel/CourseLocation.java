package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="course_locations")
public class CourseLocation extends BaseModel {

    private Date scheduleDate;
    // Quan hệ một nhiều với khóa học

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course courses;
    // Quan hệ một nhiều với địa điểm

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location locations;
}
