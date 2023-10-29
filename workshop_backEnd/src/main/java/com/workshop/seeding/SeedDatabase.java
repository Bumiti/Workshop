package com.workshop.seeding;
import com.workshop.dto.useDTO.UserRegisterRequest;
import com.workshop.model.Location;
import com.workshop.model.userModel.Roles;
import com.workshop.reposetory.User.UserRepository;
import com.workshop.service.LocationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.workshop.service.UserService;
import java.io.*;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SeedDatabase {
    private final UserService userService;
    private final LocationService locationService;
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
    private void addRandomTeachers() {
        String[] names = {"John", "Alice", "Bob", "Emily", "Michael", "Sarah", "David", "Olivia", "Daniel", "Sophia", "William", "Emma", "James", "Ava", "Matthew", "Chloe", "Jacob", "Mia", "Ethan", "Lily"};
        String[] genders = {"male", "female"};
        Random random = new Random();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for (int i = 0; i < 20; i++) {
            String randomName = names[random.nextInt(names.length)];
            String randomGender = genders[random.nextInt(genders.length)];
            String username = "teacher" + (i + 1);
            String email = username + "@gmail.com";
            String password = "12345"; // Mật khẩu tạm thời
            String phone = "0383334195";

            String encodedPassword = passwordEncoder.encode(password);

            UserRegisterRequest teacher = new UserRegisterRequest();
            teacher.setUser_name(randomName);
            teacher.setFull_name(randomName);
            teacher.setEmail(email);
            teacher.setPassword(encodedPassword);
            teacher.setPhoneNumber(phone);
            teacher.setGender(randomGender);
            teacher.setRole("SELLER");
            teacher.setEnable(true);
            userService.SaveUser(teacher);
        }
    }
    private void addLocation() {
        locationService.AddLocation(new Location("Adora Plaza", "Quận 1", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Diamond Center", "Quận 1", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Vincom Center", "Quận 1", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("BlackPear Plaza", "Quận 2", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Mega Mall", "Quận 2", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Riverside Shopping", "Quận 2", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("VinCom Plaza", "Quận 3", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Crescent Mall", "Quận 3", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Takashimaya", "Quận 3", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Now Zone", "Quận 4", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Pearl Plaza", "Quận 4", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Saigon Paragon Mall", "Quận 4", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Lotte Mart", "Quận 5", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Pandora City", "Quận 5", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("An Dong Plaza", "Quận 5", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Now Mega Mall", "Quận 6", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Parkson CT Plaza", "Quận 6", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("CitiMart Shopping", "Quận 6", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("SC VivoCity", "Quận 7", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Crescent Mall 2", "Quận 7", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Lotte Mart 2", "Quận 7", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("AEON Mall Tan Phu Celadon", "Quận 8", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("The Garden Mall", "Quận 8", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Dragon Mall", "Quận 8", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Vincom Mega Mall", "Quận 9", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Crescent Mall 3", "Quận 9", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Takashimaya 2", "Quận 9", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Now Zone 2", "Quận 10", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("Parkson Shopping", "Quận 10", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Central Square", "Quận 10", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Lotte Mart 3", "Quận 11", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("AEON Mall 2", "Quận 11", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Mega Plaza", "Quận 11", "Trung Tâm 3", null, null));
        locationService.AddLocation(new Location("Vincom Mega Mall 2", "Quận 12", "Trung Tâm 1", null, null));
        locationService.AddLocation(new Location("CitiMart 2", "Quận 12", "Trung Tâm 2", null, null));
        locationService.AddLocation(new Location("Super Plaza", "Quận 12", "Trung Tâm 3", null, null));
    }

    @PostConstruct
    public void SeedData() {
        if (isSeedCompleted()) {
            System.out.println("Seed has run before. Do not do it again");
        } else {

            userService.SaveRoles(new Roles(null, "USER"));
            userService.SaveRoles(new Roles(null, "SELLER"));
            userService.SaveRoles(new Roles(null, "ADMIN"));
            userService.SaveUser(new UserRegisterRequest("NguyenAdmin", "admin64",
                    "admin64@gmail.com", "admin64@gmail.com", "0383334196", "female", "ADMIN", true));
            userService.SaveUser(new UserRegisterRequest("lactuong64@gmail.com", "lactuong64@gmail.com",
                    "student@gmail.com", "student@gmail.com", "0383334195", "male", "USER", true));
            userService.SaveUser(new UserRegisterRequest("teacher01", "teacher01",
                    "teacher01@gmail.com", "teacher01@gmail.com", "0383334195", "male", "SELLER", true));
           addRandomTeachers();
            addLocation();
            createSeedStatusFile();
        }

    }
}