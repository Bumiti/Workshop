package com.workshop.dto.RequestDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.workshop.model.Request;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RequestDTO {

    private String type;
    private String status = "PENDING";
//    private Long userId;
    private Long item_register_id;
    private Long locationId;
    //gửi
    private Double amount;
    //thanh toán
    private String paymentName;
    private String paymentStatus;
    @Builder.Default
    private LocalDateTime registrationDateTime = LocalDateTime.now();
}
