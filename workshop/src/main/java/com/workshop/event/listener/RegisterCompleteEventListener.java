package com.workshop.event.listener;

import com.workshop.event.RegisterCompleteEvent;
import com.workshop.model.userModel.User;
import com.workshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterCompleteEventListener
        implements ApplicationListener<RegisterCompleteEvent>
{
private final UserService userService;
    @Override
    public void onApplicationEvent(RegisterCompleteEvent event) {
        //1. Get New user
        User user = event.getUser();
        //2.Create VeryCode token for User
        String verificationToken = UUID.randomUUID().toString();
        //3.Save VerCode token for User
        userService.saveUserVerificationToken(user,verificationToken);
        //4.Build  the VerCode url to be sent
        String url = event.getUrl()+"/auth/register/verifyEmail?token="+verificationToken;
        //5.Send Email
        log.info("Click to link to very your account :{}",url);
    }
}
