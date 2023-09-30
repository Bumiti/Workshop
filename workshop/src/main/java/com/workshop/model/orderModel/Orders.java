package com.workshop.model.orderModel;

import com.workshop.model.BaseModel;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="orders")
public class Orders extends BaseModel {
    private String order_name;
    private Date order_date;
    private float TotalAmount;
    private String order_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User user;

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    private Set<OrderDetails> orderDetails = new HashSet<>();
}
