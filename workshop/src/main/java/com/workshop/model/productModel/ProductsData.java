package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product_data")
public class ProductsData extends BaseModel {
    private String url_image;
    private String url_video;
    @ManyToOne
    @JoinColumn(name = "product_fkid")
    private Products products;

}
