package com.workshop.config.cloud;

import lombok.*;

import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseRequestOptions {
    private String user_name;
    private String email;
    private String urlQrCode;
    private String status;
}
