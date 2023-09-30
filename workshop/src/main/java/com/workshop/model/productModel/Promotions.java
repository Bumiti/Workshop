package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="promotions")
public class Promotions extends BaseModel {
    private String promotion_name;
    private String description;
    private String start_date;
    private String end_date;
    private String discount_amount;
    @OneToMany(mappedBy = "promotions", fetch = FetchType.LAZY)
    private Set<ProductPromotions> promotions = new HashSet<>();
}
