package com.workshop.seeding;

import com.workshop.dto.CourseRequest;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.userModel.Roles;
import com.workshop.service.CourseService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshop.service.UserService;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;

@Service
public class SeedDatabase {

    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;
    private static final String SEED_STATUS_FILE_PATH = "seed_status.txt";
    private boolean isSeedCompleted() {
        return new File(SEED_STATUS_FILE_PATH).exists();
    }
    private void createSeedStatusFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SEED_STATUS_FILE_PATH))) {
            writer.write("Seed has run and completed");
            System.out.println("Seed status file created successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @PostConstruct
    public void SeedData()
    {
        if (isSeedCompleted()) {
            System.out.println("Seed has run before. Do not do it again");
        }
       else
       {
           userService.SaveRoles(new Roles(null,"USER"));
           userService.SaveRoles(new Roles(null,"SELLER"));
           userService.SaveRoles(new Roles(null,"ADMIN"));
           userService.SaveUser(new UserRegisterRequest("NguyenAdmin","admin64",
                   "admin64@gmail.com","12345",true));
           userService.SaveUser(new UserRegisterRequest("NguyenSeller","seller64",
                   "seller64@gmail.com","12345",true));
           userService.SaveUser(new UserRegisterRequest("NguyenUser","user64",
                   "user64@gmail.com","12345",true));
           userService.SaveUser(new UserRegisterRequest("NguyenUser1", "user1",
                   "user1@gmail.com", "12345", true));
           userService.SaveUser(new UserRegisterRequest("NguyenUser2", "user2",
                   "user2@gmail.com", "12345", true));
           userService.SaveUser(new UserRegisterRequest("NguyenUser3", "user3",
                   "user3@gmail.com", "12345", true));
           userService.SaveUser(new UserRegisterRequest("NguyenSeller1", "seller1",
                   "seller1@gmail.com", "12345", true));
           userService.SaveUser(new UserRegisterRequest("NguyenSeller2", "seller2",
                   "seller2@gmail.com", "12345", true));
           userService.AddRoleToUser("admin64@gmail.com","ADMIN");
           userService.AddRoleToUser("seller64@gmail.com","SELLER");
           userService.AddRoleToUser("seller1@gmail.com", "SELLER");
           userService.AddRoleToUser("seller2@gmail.com", "SELLER");
           userService.AddRoleToUser("user64@gmail.com","USER");
           userService.AddRoleToUser("user1@gmail.com", "USER");
           userService.AddRoleToUser("user2@gmail.com", "USER");
           userService.AddRoleToUser("user3@gmail.com", "USER");





           createSeedStatusFile();
       }
    }
}