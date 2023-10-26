package com.workshop.dto.useDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.Accessors;


import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfoResponse {
    private Long id;
    private String full_name;
    private String user_name;
    private String email;
    private String phoneNumber;
    private String image_url;
    private String gender;
    private List<String> roles;
    private boolean isEnable ;
    @JsonProperty("userAddresses")
    @Nullable
    @Builder.Default
    private List<UserAddress> userAddresses= Collections.emptyList();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAddress {
        private Long id;
        private String Address;
        private String City;
        private String State;
        private int PostalCode;
    }
}
