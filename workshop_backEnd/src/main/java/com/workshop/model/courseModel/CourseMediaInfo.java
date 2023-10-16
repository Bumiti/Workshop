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
@Table(name="course_media_info")
public class CourseMediaInfo extends BaseModel {

    private String urlMedia;
    private String urlImage;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
