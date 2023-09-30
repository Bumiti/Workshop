package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import com.workshop.model.userModel.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_reports")
public class ProductsReports extends BaseModel {
    private String report_type;
    private String description;
    private Date report_date;

    @ManyToOne
    @JoinColumn(name = "product_FkId")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "user_buyer_FkId")
    private User user;
}
