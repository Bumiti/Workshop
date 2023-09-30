package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="productReview")
public class ProductReview extends BaseModel {
    private float rating;
    private String review_text;
    private Date review_date;

    @ManyToOne
    @JoinColumn(name = "product_fkid")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "user_fkid")
    private User user;
}
