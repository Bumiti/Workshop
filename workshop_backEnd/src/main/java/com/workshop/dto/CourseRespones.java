package com.workshop.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseRespones {
    private String name;
    private String description;
    private double price;
    private Timestamp startDate;
    private Timestamp endDate;
    private boolean type;
    private List<StudentEnrollment> studentEnrollments;
    public static class StudentEnrollment {
        private Long id;
        private String name;
        public StudentEnrollment(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public StudentEnrollment() {

        }

        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
