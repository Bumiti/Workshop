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
public class WorkshopRespones {
    private String name;
    private String description;
    private double price;
    private Date date;
    private Date time;
    private String type;
    private boolean isApproved = false;
    private String teacher;
    private String location;
    private int discount;
//    private List<StudentEnrollment> studentEnrollments;
//    private List<Transaction> transactions;
//    public static class Transaction {
//        private double amount;
//        private String status;
//        private Date transactionDate;
//
//    }
//    public static class StudentEnrollment {
//        private Long id;
//        private String name;
//        private String urlImage;
//        private String statusPayment;
//    }
}
