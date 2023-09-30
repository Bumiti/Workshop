package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import com.workshop.model.orderModel.OrderDetails;
import com.workshop.model.storeModel.StoreFollowers;
import com.workshop.model.userModel.User;
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
    @Column(name="status")
    private  boolean status;

    @ManyToOne
    @JoinColumn(name = "product_FkId")
    private ProductCategory product_Category;
    @ManyToOne
    @JoinColumn(name = "user_buyer_FkId")
    private User user;

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductReview> productReviews = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductsData> productsData = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductsReports> productsReports = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<OrderDetails> orderDetails = new HashSet<>();

    @OneToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ProductPromotions> productPromotions = new HashSet<>();


}
