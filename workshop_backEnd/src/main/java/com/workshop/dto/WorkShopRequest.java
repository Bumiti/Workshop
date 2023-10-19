package com.workshop.dto;

import lombok.*;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkShopRequest {
    private String name;
    private String description;
    private double price;
    private Date date;
    private Date time;
    private String type;
    private boolean isApproved ;
    private LocationDto location;
    private List<WorkshopDiscountDto> workshopDiscount;
    private List<Long> student_Enrollments_Id;
    public static class LocationDto {
        private String name;
        private String address;
        private String description;
    }
    public static class WorkshopDiscountDto {
        private String name;
        private String description;
        private int remainingUses;
        private Date redemptionDate;
    }

}
