package com.workshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workshop.model.BaseModel;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseRespones {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private String type;
    private String Teacher;
    private boolean isPublic;
    @JsonProperty("studentEnrollments")
    private List<StudentEnrollment> studentEnrollments;
    @JsonProperty("courseMediaInfos")
    private List<CourseMediaInfo> courseMediaInfos;
    @JsonProperty("courseLocations")
    private List<CourseLocation> courseLocations;
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentEnrollment {
        private Long id;
        private String name;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public  static  class CourseMediaInfo{
        private Long id;
        private String urlMedia;
        private String urlImage;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseLocation{
        private Long id;
        private String name;
        private String address;
        private String description;
        private Date scheduleDate;

    }
}
