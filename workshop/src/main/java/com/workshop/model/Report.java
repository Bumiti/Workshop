package com.workshop.model;

import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="report")
public class Report extends BaseModel{


    @Column(name = "ReportType")
    private String reportType;

    @Column(name = "Description", columnDefinition = "text")
    private String description;

    @Column(name = "ReportDate")
    private Date reportDate;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReportedUserID")
    private User reportedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ReporterUserID")
    private User reporterUser;
}
