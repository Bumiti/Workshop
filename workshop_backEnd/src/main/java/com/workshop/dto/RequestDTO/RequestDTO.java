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

    private Long item_register_id;

    private Long locationId;

    private Double amount;

    private Double discountAmount;
    private String discountCode;

    private String paymentName;
    private String paymentStatus;
    @Builder.Default
    private LocalDateTime registrationDateTime = LocalDateTime.now();
}
