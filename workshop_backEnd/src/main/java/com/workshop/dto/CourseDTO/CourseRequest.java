package com.workshop.dto.CourseDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
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