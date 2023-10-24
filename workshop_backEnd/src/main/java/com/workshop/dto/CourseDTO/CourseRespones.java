package com.workshop.dto.CourseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import java.sql.Timestamp;
import java.util.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseRespones {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private double price;
    @NotNull
    private Timestamp startDate;
    @NotNull
    private Timestamp endDate;
    @NotNull
    private int student_count;
    @NotNull
    private String type;
    @NotNull
    private String Teacher;
    @NotNull
    private boolean isPublic;

    @JsonProperty("studentEnrollments")
    @Nullable
    @Builder.Default
    private List<StudentEnrollment> studentEnrollments = Collections.emptyList();
    @JsonProperty("courseMediaInfos")
    @Nullable
    @Builder.Default
    private List<CourseMediaInfo> courseMediaInfos = Collections.emptyList();
    @JsonProperty("courseLocations")
    @Nullable
    @Builder.Default
    private List<CourseLocation> courseLocations = Collections.emptyList();
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
    public static class CourseMediaInfo {
        private Long id;
        private String urlMedia;
        private String urlImage;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseLocation {
        private Long id;
        private String Area;
        private Date schedule_Date;
        @Nullable
        @JsonProperty("locationDTO")
        private locationResponse locationResponse = new locationResponse();

        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        public static class locationResponse {
            private String name;
            private String address;
            private String description;
        }
    }
}
