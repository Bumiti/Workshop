package com.workshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserEditRequest {
    private String full_name;
    private String user_name;
    private String email;
    private String password;
    private String role;
    private List<UserAddress> userAddresses;

    public static class UserAddress {
        private String Address;
        private String City;
        private String State;
        private int PostalCode;

    }
}
