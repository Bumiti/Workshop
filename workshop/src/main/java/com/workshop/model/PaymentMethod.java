package com.workshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="payment_method")
public class PaymentMethod extends BaseModel{
    private String name;
    private String description;

    // Quan hệ một nhiều với thanh toán
    @OneToMany(mappedBy = "paymentMethod")
    private List<Transaction> transactions;

}
