package com.workshop.model.Campaigns;

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
@Table(name="ad_campaignStatistics")
public class AdCampaignStatistics extends BaseModel {
    private Date Date;
    private int Impressions;
    private int Clicks;
    private float Conversions;
    private int Spend;

    @ManyToOne
    @JoinColumn(name = "campaignfk_id")
    private AdCampaigns adCampaigns;
}
