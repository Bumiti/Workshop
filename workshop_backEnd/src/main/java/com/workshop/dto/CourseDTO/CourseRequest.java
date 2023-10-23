package com.workshop.dto.CourseDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.*;

import java.sql.Timestamp;
import java.util.Collections;
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
    @JsonProperty("MediaInfoList")
    @Nullable
    @Builder.Default
    private List<CourseMediaInfoDTOS> MediaInfoList = Collections.emptyList();
    @JsonProperty("discountDTOS")
    @Nullable
    @Builder.Default
    private List<DiscountDTO> discountDTOS = Collections.emptyList();

    @JsonProperty("courseLocation")
    @Nullable
    @Builder.Default
    private List<CourseLocation> courseLocation = Collections.emptyList();

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
        //số lượng
        private int quantity;
        //ngày hết hạn
        private Date redemptionDate;

        //value của discount này
        private int valueDiscount ;
        private String name;
        private String description;
        private int remainingUses;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CourseLocation {
        private String Area;
        private Date schedule_Date;
    }

}
