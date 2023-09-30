package com.workshop.model.productModel;

import com.workshop.model.BaseModel;
import com.workshop.model.Campaigns.AdCampaigns;
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
@Table(name="product_promotions")
public class ProductPromotions extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "productsfk_id")
    private Products products;
    @ManyToOne
    @JoinColumn(name = "promotionsfk_id")
    private Promotions promotions;

}
