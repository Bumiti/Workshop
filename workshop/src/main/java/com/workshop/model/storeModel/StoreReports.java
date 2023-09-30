package com.workshop.model.storeModel;

import com.workshop.model.BaseModel;
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
@Table(name="store_reports")
public class StoreReports extends BaseModel {
    @Column(name="report_Type")
    private String report_Type;
    @Column(name="description")
    private String description;
    @Column(name="report_date")
    private Date report_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_fkid")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_fkid")
    private User user;
}
