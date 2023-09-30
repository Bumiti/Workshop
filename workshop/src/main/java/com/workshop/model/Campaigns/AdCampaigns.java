package com.workshop.model.Campaigns;

import com.workshop.model.BaseModel;
import com.workshop.model.orderModel.Orders;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ad_campaigns")
public class AdCampaigns extends BaseModel {
    private String CampaignName;
    private String Description;
    private String Budget;
    private String CampaignStatus;
    private Date StartDate;
    private Date EndDate;
    @OneToMany(mappedBy = "adCampaigns", fetch = FetchType.LAZY)
    private Set<AdCampaignStatistics> adCampaignStatistics = new HashSet<>();
}
