package com.workshop.model.workshopModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Location;
import com.workshop.model.Request;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.Date;
import java.util.List;

import com.workshop.model.userModel.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="workshop")
public class Workshop extends BaseModel {
    private String name;
    private String description;
    private double price;
    private Date date;
    private Date time;
    private String type;
    private boolean isApproved = false;

    // Quan hệ nhiều một với giáo viên tổ chức
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;


    @OneToMany(mappedBy = "workshop")
    private List<WorkshopEnrollment> enrolledStudents;
    @OneToMany(mappedBy = "workshop")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Request> requests;
    @OneToMany(mappedBy = "workshop")
    private List<WorkshopDiscount> workshopDiscounts;
    // Quan hệ một nhiều với địa điểm tổ chức
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
