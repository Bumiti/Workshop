package com.workshop.model;

import com.workshop.model.workshopModel.Workshop;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name="location")
public class Location extends BaseModel{
    private String name;
    private String address;
    private String description;
    // Quan hệ một nhiều với buổi workshop có thể tổ chức tại đây
    @OneToMany(mappedBy = "location")
    private List<Workshop> workshops;
}