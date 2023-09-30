package com.workshop.model.storeModel;

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

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="store_followers")
public class StoreFollowers extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "user_FkId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_FkId")
    private Store store;

}
