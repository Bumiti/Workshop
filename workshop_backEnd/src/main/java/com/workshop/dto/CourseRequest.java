package com.workshop.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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
    @JsonProperty("MediaInforList")
    private List<CourseMediaInfoDTOS> MediaInforList;

    @JsonProperty("discountDTOS")
    private List<DiscountDTO> discountDTOS;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseMediaInfoDTOS{
        private String urlMedia;
        private String urlImage;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DiscountDTO {
        //ngày hết hạn
        private int quantity;
        private Date redemptionDate;
        //value của discount này
        private String name;
        private String description;
        private int remainingUses;
    }

}
