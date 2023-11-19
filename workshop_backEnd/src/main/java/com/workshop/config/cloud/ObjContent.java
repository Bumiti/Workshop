package com.workshop.config.cloud;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ObjContent {
    private String name;
    private String email;
    private boolean status;
}
