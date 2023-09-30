package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Report;
import com.workshop.model.storeModel.Store;
import jakarta.persistence.*;
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
@Table(name="product_category")
public class ProductCategory extends BaseModel {
    private String product_CategoryName;

    @OneToMany(mappedBy = "product_Category", fetch = FetchType.LAZY)
    private Set<Products> products= new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "productCateogory_FkId")
    private Store store;
}
