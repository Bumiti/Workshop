package com.workshop.seeding;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshop.service.UserService;

@Service
public class SeedDatabase {

    @Autowired
    private UserService userService;
    @PostConstruct

    public void SeedData(){
//        userService.SaveRoles(new Roles(null,"USER"));
//        userService.SaveRoles(new Roles(null,"SELLER"));
//        userService.SaveRoles(new Roles(null,"ADMIN"));
//        userService.SaveUser(new User("NguyenAdmin","admin64", "admin64@gmail.com","12345",new HashSet<>()));
//        userService.SaveUser(new User("NguyenSeller","seller64",
//                "seller64@gmail.com","12345",new HashSet<>()));
//        userService.SaveUser(new User("NguyenUser","user64",
//                "user64@gmail.com","12345",new HashSet<>()));
//
//
//        userService.AddRoleToUser("admin64@gmail.com","ADMIN");
//        userService.AddRoleToUser("seller64@gmail.com","SELLER");
//        userService.AddRoleToUser("user64@gmail.com","USER");
    }
}