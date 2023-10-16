package com.workshop.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInforRespone {
    private String full_name;
    private String user_name;
    private String email;
    private String phoneNumber;
    private String address;
    private String image;
    private boolean isEnable ;
}
