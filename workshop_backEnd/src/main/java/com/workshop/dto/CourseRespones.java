package com.workshop.dto;

import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
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
    private boolean isPublic;
    private List<StudentEnrollment> studentEnrollments;
    private List<CourseInfoMedia> courseInfoMedia;
    private List<CourseLocation> courseLocations;
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
    public  static  class CourseInfoMedia{
        private Long id;
        private String urlMedia;
        private String urlImage;
        public CourseInfoMedia(Long id, String urlMedia, String urlImage) {
            this.id = id;
            this.urlMedia = urlMedia;
            this.urlImage = urlImage;
        }
        public CourseInfoMedia() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUrlMedia() {
            return urlMedia;
        }

        public void setUrlMedia(String urlMedia) {
            this.urlMedia = urlMedia;
        }

        public String getUrlImage() {
            return urlImage;
        }

        public void setUrlImage(String urlImage) {
            this.urlImage = urlImage;
        }
    }
    public static class CourseLocation{
        private Long id;
        private String name;
        private String address;
        private String description;
        private Date scheduleDate;

        public CourseLocation(Long id, String name, String address, String description, Date scheduleDate) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.description = description;
            this.scheduleDate = scheduleDate;
        }

        public CourseLocation() {

        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getScheduleDate() {
            return scheduleDate;
        }

        public void setScheduleDate(Date scheduleDate) {
            this.scheduleDate = scheduleDate;
        }
    }
}
