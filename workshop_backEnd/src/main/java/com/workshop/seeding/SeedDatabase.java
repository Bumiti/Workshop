package com.workshop.seeding;

import com.workshop.dto.CourseRequest;
import com.workshop.dto.UserRegisterRequest;
import com.workshop.model.Location;
import com.workshop.model.userModel.Roles;
import com.workshop.service.CourseService;
import com.workshop.service.LocationService;
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
    @Autowired
    private LocationService locationService;
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
                   "admin64@gmail.com","12345","ADMIN",true));

           userService.SaveUser(new UserRegisterRequest("teacher01","teacher01",
                   "teacher01@gmail.com","12345","SELLER",true));
           userService.SaveUser(new UserRegisterRequest("teacher02","teacher02",
                   "teacher02@gmail.com","12345","SELLER",true));
           userService.SaveUser(new UserRegisterRequest("teacher03","teacher03",
                   "teacher03@gmail.com","12345","SELLER",true));
           userService.SaveUser(new UserRegisterRequest("teacher04","teacher04",
                   "teacher04@gmail.com","12345","SELLER",true));
           userService.SaveUser(new UserRegisterRequest("teacher05","teacher05",
                   "teacher05@gmail.com","12345","SELLER",true));

           userService.SaveUser(new UserRegisterRequest("student01","student01",
                   "student01@gmail.com","12345","USER",true));
           userService.SaveUser(new UserRegisterRequest("student02","student02",
                   "student02@gmail.com","12345","USER",true));
           userService.SaveUser(new UserRegisterRequest("student03","student03",
                   "student03@gmail.com","12345","USER",true));
           userService.SaveUser(new UserRegisterRequest("student04","student04",
                   "student04@gmail.com","12345","USER",true));
           userService.SaveUser(new UserRegisterRequest("student05","student05",
                   "student05@gmail.com","12345","USER",true));
           userService.SaveUser(new UserRegisterRequest("NguyenUser","student06",
                   "student06@gmail.com","12345","USER",true));
           locationService.AddLocation(new Location("Adora Plaza","Quận 1","Trung Tâm",null,null));
           locationService.AddLocation(new Location("BlackPear Plaza","Quận 2","Trung Tâm",null,null));
           locationService.AddLocation(new Location("VinCom Plaza","Quận 3","Trung Tâm",null,null));

           createSeedStatusFile();
       }
    }
}