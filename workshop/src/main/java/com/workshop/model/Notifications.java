package com.workshop.model;

import com.workshop.model.userModel.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="notifications")
public class Notifications extends BaseModel  {

    @Column(name="message")
    private  String Message;
    @Column(name="notification_Date")
    private Date notification_Date;

    @ManyToOne
    @JoinColumn(name = "userFkId")
    private User user;
}
