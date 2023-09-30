package com.workshop.model.storeModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workshop.model.BaseModel;
import com.workshop.model.productModel.ProductCategory;
import com.workshop.model.productModel.Products;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store")
public class Store extends BaseModel {
    @Column(name="store_name")
    private String StoreName;
    @Column(name="address")
    private String Address;
    @Column(name="description")
    private String Description;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OwnerID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_CategoryFkID")
    private StoreCategory storeCategories;

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<StoreFollowers> storeFollowers = new HashSet<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<StoreReports> storeReports = new HashSet<>();

    @OneToMany(mappedBy = "store", fetch = FetchType.LAZY)
    private Set<ProductCategory> productCategories= new HashSet<>();

}
