package com.workshop.dto;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserEditRequest {
    private String full_name;
    private String user_name;
    private String email;
    private String phoneNumber;
    private String image_url;
    private String roles;
    private ArrayList<UserAddress> userAddresses;

    public static class UserAddress {
        private String Address;
        private String City;
        private String State;
        private int PostalCode;

        public UserAddress(String address, String city, String state, int postalCode) {
            Address = address;
            City = city;
            State = state;
            PostalCode = postalCode;
        }
        public UserAddress() {
        }
        public String getAddress() {
            return Address;
        }
        public void setAddress(String address) {
            Address = address;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String city) {
            City = city;
        }

        public String getState() {
            return State;
        }

        public void setState(String state) {
            State = state;
        }

        public int getPostalCode() {
            return PostalCode;
        }

        public void setPostalCode(int postalCode) {
            PostalCode = postalCode;
        }
    }

}
