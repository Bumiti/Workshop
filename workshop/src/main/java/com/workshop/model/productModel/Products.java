package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Products extends BaseModel {
    @Column(name="product_Name")
    private String product_Name;
    @Column(name="description")
    private String description;
    @Column(name="price")
    private Long price;
    @Column(name="stock_Quantity")
    private Long stock_Quantity;

    @ManyToOne
    @JoinColumn(name = "product_FkId")
    private ProductCategory product_Category;
}
