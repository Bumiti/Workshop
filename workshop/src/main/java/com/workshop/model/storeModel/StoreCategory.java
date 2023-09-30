package com.workshop.model.storeModel;


import com.workshop.model.BaseModel;
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
@Table(name="store_category")
public class StoreCategory extends BaseModel {

    @Column(name="store_CategoryName")
    private String store_CategoryName;

    @OneToMany(mappedBy = "storeCategories",fetch = FetchType.LAZY)
    private Set<Store>store = new HashSet<>();
}
