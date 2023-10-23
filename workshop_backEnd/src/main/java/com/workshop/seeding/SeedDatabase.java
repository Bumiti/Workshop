package com.workshop.seeding;


import com.workshop.dto.useDTO.UserRegisterRequest;
import com.workshop.model.Location;
import com.workshop.model.userModel.Roles;
import com.workshop.service.LocationService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshop.service.UserService;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


@Service
public class SeedDatabase {

    @Autowired
    private UserService userService;
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
                   "admin64@gmail.com","12345","0383334196","female","ADMIN",true));
           userService.SaveUser(new UserRegisterRequest("lactuong64@gmail.com","lactuong64@gmail.com",
                   "lactuong64@gmail.com","12345","0383334195","male","USER",true));
           userService.SaveUser(new UserRegisterRequest("teacher01","teacher01",
                   "teacher01@gmail.com","12345","0383334195","male","SELLER",true));

           locationService.AddLocation(new Location("Adora Plaza","Quận 1","Trung Tâm",null,null));
           locationService.AddLocation(new Location("BlackPear Plaza","Quận 2","Trung Tâm",null,null));
           locationService.AddLocation(new Location("VinCom Plaza","Quận 3","Trung Tâm",null,null));

           createSeedStatusFile();
       }

    }
}