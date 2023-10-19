package com.workshop.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WorkShopRespone {
    private Long workshopId;
    private String name;
    private String description;
    private double price;
    private Date date;
    private Date time;
    private String type;
    private boolean isApproved ;
    private Location location;
    private List<WorkshopDiscount> workshopDiscount;
    private List<StudentEnrollment> studentEnrollments;
    private String teacher;
    public static class Location {
        private String name;
        private String address;
        private String description;
    }
    public static class WorkshopDiscount {
        private String name;
        private String description;
        private int remainingUses;
        private Date redemptionDate;
    }
    public static class StudentEnrollment {
        private Long id;
        private String name;
        private String urlImage;
        private String statusPayment;
    }

}
