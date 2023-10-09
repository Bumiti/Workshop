package com.workshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "workshop_discounts")
public class WorkshopDiscount extends BaseModel{
    private Date redemptionDate;

    // Quan hệ một nhiều với buổi workshop
    @ManyToOne
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    // Quan hệ một nhiều với ưu đãi
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;
}
