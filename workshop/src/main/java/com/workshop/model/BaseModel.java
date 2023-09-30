package com.workshop.model;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import jakarta.persistence.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="create_by")
    private String create_by;
    @Column(name="update_by",nullable = true)
    private String update_by;

    @Column(name="create_day")
    private Date create_day;

    @Column(name="update_day",nullable = true)
    private Date update_day;


}
