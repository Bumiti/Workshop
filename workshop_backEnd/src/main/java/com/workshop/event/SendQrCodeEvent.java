package com.workshop.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SendQrCodeEvent extends ApplicationEvent {
    private String user_name;
    private String email;
    private String url;

    public SendQrCodeEvent(String user_name,String email, String url) {
        super(user_name);
        this.user_name = user_name;
        this.email = email;
        this.url = url;

    }
}
