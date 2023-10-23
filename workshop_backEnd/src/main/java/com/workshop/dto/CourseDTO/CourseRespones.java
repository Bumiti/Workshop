package com.workshop.dto.CourseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.workshop.model.BaseModel;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Collections;
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
    @Nullable
    @Builder.Default
    private List<StudentEnrollment> studentEnrollments= Collections.emptyList();
    @JsonProperty("courseMediaInfos")
    @Nullable
    @Builder.Default
    private List<CourseMediaInfo> courseMediaInfos= Collections.emptyList();
    @JsonProperty("courseLocations")
    @Nullable
    @Builder.Default
    private List<CourseLocation> courseLocations= Collections.emptyList();
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
