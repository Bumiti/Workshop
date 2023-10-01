package com.workshop.model;

import jakarta.persistence.Entity;
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
@Table(name="khoahoc")
public class KhoaHoc extends BaseModel{
    private String maKhoaHoc;
    private String urlKhoaHoc;
    private String tenKhoaHoc;
    private Date DateStart;
    private String Description;
    private Long SoLuongUngVien;
    private float GiaTien;
}
