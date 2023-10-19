package com.workshop.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequest {
    private String name;
    private String description;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private int student_count;
    private String type;
    private List<CourseMediaInfoDTOS> MediaInforList;
    private List<DiscountDTO> discountList;
    private List<StudentEnrolled> studentEnrolledList;
    public static class StudentEnrolled{
        private String urlMedia;
        private String urlImage;
    }
    public static class CourseMediaInfoDTOS{
        private String urlMedia;
        private String urlImage;
    }
    public static class DiscountDTO {
        private String name;
        private String description;
        private int remainingUses;
    }

}
