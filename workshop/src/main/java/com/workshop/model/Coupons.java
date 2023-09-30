package com.workshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workshop.model.userModel.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="coupons")
public class Coupons extends BaseModel {
    @Column(name = "coupon_Code")
    private Long couponCode;
    @Column(name = "description")
    private String description;
    @Column(name = "discount_Amount")
    private  float discountAmount;
    @Column(name = "expiry_Date")
    private Date expiryDate;

    @ManyToMany(mappedBy ="coupons")
    @Fetch(value = FetchMode.SELECT)
    @JsonIgnore
    private Set<User> user = new HashSet<>();
}
