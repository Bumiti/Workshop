package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Location;
import com.workshop.model.courseModel.Course;
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
@Table(name="course_locations")
public class CourseLocation extends BaseModel {

    private Date scheduleDate;
    // Quan hệ một nhiều với khóa học
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course_offline;

    // Quan hệ một nhiều với địa điểm
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
