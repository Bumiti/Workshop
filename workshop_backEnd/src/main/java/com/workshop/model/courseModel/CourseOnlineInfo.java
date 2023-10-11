package com.workshop.model.courseModel;

import com.workshop.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="course_online_info")
public class CourseOnlineInfo extends BaseModel {

    private String urlContent;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course_online;
}
