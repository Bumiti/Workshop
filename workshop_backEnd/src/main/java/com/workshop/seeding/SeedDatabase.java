package com.workshop.seeding;

import com.workshop.dto.useDTO.UserRegisterRequest;
import com.workshop.model.*;
import com.workshop.model.courseModel.*;
import com.workshop.model.userModel.*;
import com.workshop.repositories.Course.*;
import com.workshop.repositories.*;
import com.workshop.repositories.User.*;
import com.workshop.service.LocationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.workshop.service.UserService;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SeedDatabase {
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserBankRepository userBankRepository;
    private final UserAddressRepository userAddressRepository;
    private final RoleRepository roleRepository;
    private final CourseRepository courseRepository;
    private final LocationService locationService;
    private final CourseMediaInfoRepository courseMediaInfoRepository;
    private final CourseLocationRepository courseLocationRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final DiscountRepository discountRepository;
    private final CourseDiscountRepository courseDiscountRepository;
    private static final String SEED_STATUS_FILE_PATH = "seed_status.txt";

    private boolean isSeedCompleted() {
        return new File(SEED_STATUS_FILE_PATH).exists();
    }

    private void createSeedStatusFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SEED_STATUS_FILE_PATH))) {
            writer.write("Seed has run and completed");
            System.out.println("Seed status file created successfully");
        } catch (IOException e) {
            System.out.println(e.getMessage());        }
    }

    @PostConstruct
    public void SeedData() {
        if (isSeedCompleted()) {
            System.out.println("Seed has run before. Do not do it again");
        } else {
            userService.SaveRoles(new Roles(null, "USER"));
            userService.SaveRoles(new Roles(null, "SELLER"));
            userService.SaveRoles(new Roles(null, "ADMIN"));
            addServiceManager();
            addRandomTeachers();
            addLocation();
            addCourse();
            createSeedStatusFile();
        }

    }
    private void addServiceManager(){
        User Manager = new User();
        User Seller = new User();
        User User = new User();
        List<Roles> rolesList = roleRepository.findAll();
        Roles rolesUser = rolesList.get(0);
        Roles rolesSeller= rolesList.get(1);
        Roles rolesAdmin = rolesList.get(2);
        Set<Roles> rolesSetUser = new HashSet<>();
        Set<Roles> rolesSetSeller = new HashSet<>();
        Set<Roles> rolesSetAdmin = new HashSet<>();
        rolesSetUser.add(rolesUser);
        rolesSetSeller.add(rolesSeller);
        rolesSetAdmin.add(rolesAdmin);
        Manager.setFull_name("Nguyễn Hồng Quân").setBalance(5000.0).setUser_name("HongQuan").setEmail("admin64@gmail.com").setPassword("admin64@gmail.com").setPhoneNumber("0383334196").setGender("male").setRoles(rolesSetAdmin).setEnable(true);
        Seller.setFull_name("Phan Huỳnh Hồng Hân").setBalance(5000.0).setUser_name("Hồng Hân").setEmail("han2000@gmail.com").setPassword("han2000@gmail.com").setPhoneNumber("097865848").setGender("female").setRoles(rolesSetSeller).setEnable(true);
        User.setFull_name("Lê Thanh Hiếu").setBalance(5000.0).setUser_name("Thanh Hiếu").setEmail("lethanhhieu@gmail.com").setPassword("lethanhhieu@gmail.com").setPhoneNumber("9833241764").setGender("male").setRoles(rolesSetUser).setEnable(true);
        userRepository.save( Manager);
        userRepository.save( Seller);
        userRepository.save( User);
    }
    private void addRandomTeachers() {
        String[] names = {"John", "Alice", "Bob", "Emily", "Michael", "Sarah", "David", "Olivia", "Daniel", "Sophia", "William", "Emma", "James", "Ava", "Matthew", "Chloe", "Jacob", "Mia", "Ethan", "Lily"};
        String[] genders = {"male", "female"};
        String[] bankName = {"ACB", "TechComBank", "DongA", "SCB", "AriBank", "VietComBank", "BIDV", "VieTinBank", "Sacombank", "MBBank", "Eximbank", "VPBank", "TPBank", "HDBank", "Agribank", "SeABank", "OceanBank", "MSB", "SHB", "NamABank", "VIB", "PVcomBank", "GPBank", "BacABank", "KienLongBank", "NCB", "ABBANK", "PGBank", "VietABank", "SCOM", "TPB", "LienvietPostBank", "VRB", "CBBank", "OCB", "LienVietBank", "ABBank", "BaoVietBank", "BVB", "VietBank", "UOB Vietnam", "Keb Hana Bank", "Shinhan Bank Vietnam", "HSBC Vietnam", "ANZ Bank Vietnam"};
        List<Roles> roles = roleRepository.findAll();
        Random random = new Random();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        for (int i = 0; i < 20; i++) {
            String randomName = names[random.nextInt(names.length)];

            String randomGender = genders[random.nextInt(genders.length)];
            Roles randomRoles = roles.get(random.nextInt(roles.size()));
            Double randomNumber = Math.random() * 500.00;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Double formattedNumber = Double.valueOf(decimalFormat.format(randomNumber));
            String username = "teacher" + (i + 1);
            String email = username + "@gmail.com";
            String password = "12345";
            String phone = "0383334195";
            String encodedPassword = passwordEncoder.encode(password);
            User user = new User();
            Set<Roles> rolesSet = new HashSet<>();
            rolesSet.add(randomRoles); // Assuming Roles is an enum

            user.setRoles(rolesSet);
            user.setUser_name(randomName)
                    .setFull_name(randomName)
                    .setEmail(email)
                    .setPassword(encodedPassword)
                    .setPhoneNumber(phone)
                    .setGender(randomGender).setBalance(formattedNumber)
                    .setEnable(true);
            userRepository.save(user);
            for (int j = 0; j < 3; j++) {
                UserAddresses address = new UserAddresses();
                address.setAddress("Street " + (j + 1));
                address.setCity("City " + (j + 1));
                address.setState("State " + (j + 1));
                address.setPostalCode(12345 * (j + 4));
                address.setUser(user);
                userAddressRepository.save(address);
            }
            for (int j = 0; j < 3; j++) {
                UserBanking userBanking = new UserBanking();
                UUID randomUUID = UUID.randomUUID();
                String randomBankAccountCode = randomUUID.toString().replaceAll("[^0-9]", "");
                String randomBank = bankName[random.nextInt(bankName.length)];
                userBanking.setBankAccount(randomBankAccountCode)
                        .setBankName(randomBank);
                userBanking.setUser(user);
                userBankRepository.save(userBanking);
            }
        }
    }
    private void addLocation() {
        locationService.AddLocation(new Location("Adora Plaza", "Quận 1", "Trung Tâm 1", null, null,null));
        locationService.AddLocation(new Location("Diamond Center", "Quận 1", "Trung Tâm 2", null, null,null));
        locationService.AddLocation(new Location("Vin-com Center", "Quận 1", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("BlackPear Plaza", "Quận 2", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Mega Mall", "Quận 2", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Riverside Shopping", "Quận 2", "Trung Tâm 3", null, null,null));
        locationService.AddLocation(new Location("VinCom Plaza", "Quận 3", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Crescent Mall", "Quận 3", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Takayoshi", "Quận 3", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Now Zone", "Quận 4", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Pearl Plaza", "Quận 4", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Saigon Paragon Mall", "Quận 4", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Lotte Mart", "Quận 5", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Pandora City", "Quận 5", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("An Dong Plaza", "Quận 5", "Trung Tâm 3", null, null,null));
        locationService.AddLocation(new Location("Now Mega Mall", "Quận 6", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Parks CT Plaza", "Quận 6", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("CitiMart Shopping", "Quận 6", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("SC VivCity", "Quận 7", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Crescent Mall 2", "Quận 7", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Lotte Mart 2", "Quận 7", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("AEON Mall Tan Phu Celadon", "Quận 8", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("The Garden Mall", "Quận 8", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Dragon Mall", "Quận 8", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Vin-com Mega Mall", "Quận 9", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Crescent Mall 3", "Quận 9", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Takayoshi 2", "Quận 9", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Now Zone 2", "Quận 10", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("Parks Shopping", "Quận 10", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Central Square", "Quận 10", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Lotte Mart 3", "Quận 11", "Trung Tâm 1",  null, null,null));
        locationService.AddLocation(new Location("AEON Mall 2", "Quận 11", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Mega Plaza", "Quận 11", "Trung Tâm 3",  null, null,null));
        locationService.AddLocation(new Location("Vin-com Mega Mall 2", "Quận 12", "Trung Tâm 1", null, null,null));
        locationService.AddLocation(new Location("CitiMart 2", "Quận 12", "Trung Tâm 2",  null, null,null));
        locationService.AddLocation(new Location("Super Plaza", "Quận 12", "Trung Tâm 3",  null, null,null));
    }
    private void addCourse() {
        String[] courseNames = {
                "C#", "C++", "Java", "JavaScript", "ASP.NET", "Sound Effect",
                "Python", "Ruby", "PHP", "Swift", "Kotlin", "Go", "Rust", "Scala", "TypeScript", "SQL", "HTML/CSS", "R", "Perl", "Haskell"
        };
        String [] listVideo ={
                "https://youtu.be/R6plN3FvzFY",
                "https://youtu.be/zwsPND378OQ",
                "https://youtu.be/ZotVkQDC6mU",
                "https://youtu.be/LYnrFSGLCl8",
                "https://youtu.be/JG0pdfdKjgQ",
                "https://youtu.be/AzmdwZ6e_aM",
                "https://youtu.be/UYpIh5pIkSA",
                "https://youtu.be/NsSsJTg29oE",
                "https://youtu.be/4J6d8cr0X48",
                "https://youtu.be/AgZ0PX28bnA",
                "https://youtu.be/x9fnxVTkpP4",
                "https://youtu.be/pcUiTt6eBk0",
        };
        String[] courseDescriptions = {"DESCRIPTION FOR", "HOW CAN LEARN", "HOT COURSE", "BEGIN FOR DEVELOPER", "WHAT YOU CAN LEARN"};
        String[] type = {"One line", "Off Line"};
        double[] coursePrices = {100.0, 200.0, 150.0, 250.0, 180.0};
        Timestamp[] startDates = {Timestamp.valueOf("2023-11-01 00:00:00"), Timestamp.valueOf("2023-11-15 00:00:00"), Timestamp.valueOf("2023-12-01 00:00:00"), Timestamp.valueOf("2023-12-15 00:00:00"), Timestamp.valueOf("2024-01-01 00:00:00")};
        Timestamp[] endDates = {Timestamp.valueOf("2024-03-01 00:00:00"), Timestamp.valueOf("2024-03-15 00:00:00"), Timestamp.valueOf("2024-04-01 00:00:00"), Timestamp.valueOf("2024-04-15 00:00:00"), Timestamp.valueOf("2024-05-01 00:00:00")};
        int min = 1;
        int max = 100;
        Random random = new Random();
        List<User> teachers = userRepository.findUsersByRoleName("SELLER");
        List<User> students = userRepository.findUsersByRoleName("USER");
        for (int i = 0; i < 20; i++) {
            int randomNumber = (int) (Math.random() * (max - min + 5) + min);
            String randomCourseName = courseNames[random.nextInt(courseNames.length)];
            boolean randomBoolean = random.nextBoolean();
            String randomCourseDescriptions = courseDescriptions[random.nextInt(courseDescriptions.length)];
            String randomType = type[random.nextInt(type.length)];
            double randomCoursePrice = coursePrices[random.nextInt(coursePrices.length)];
            Timestamp courseStartDate = startDates[random.nextInt(startDates.length)];
            Timestamp courseEndDate = endDates[random.nextInt(endDates.length)];
            User randomTeacher = teachers.get(new Random().nextInt(teachers.size()));
            String nameTeacher = randomTeacher.getUsername().toLowerCase();
            Course course = new Course();
            course.setName(randomCourseName).setStudent_count(randomNumber).setPublic(randomBoolean).setType(randomType)
                    .setDescription(randomCourseDescriptions + " " + randomCourseName)
                    .setPrice(randomCoursePrice + (i * randomNumber))
                    .setStartDate(courseStartDate)
                    .setEndDate(courseEndDate)
                    .setTeacher(randomTeacher).setCreatedBy(nameTeacher);
            courseRepository.save(course);
            for (int j = 0; j < 3; j++) {
                String randomUrlMedia = listVideo[random.nextInt(listVideo.length)];
                String randomUrlImage = "https://example.com/image" + j;
                String randomTitle = "Video Title " + j;
                CourseMediaInfo courseMediaInfo = new CourseMediaInfo();
                courseMediaInfo.setUrlMedia(randomUrlMedia).setTitle(randomTitle)
                        .setUrlImage(randomUrlImage).setThumbnailSrc("https://drive.google.com/uc?id=1Ydevpx_dppORdtxEPW-ZGJVYyWMBy20S")
                        .setCourse(course);
                courseMediaInfoRepository.save(courseMediaInfo);
                CourseLocation courseLocation = new CourseLocation();
                courseLocation.setCourses(course).setArea("District " + i).setSchedule_Date(courseStartDate);
                courseLocationRepository.save(courseLocation);
                User randomStudents;
                boolean studentFound = false;
                while (!studentFound) {
                    randomStudents = students.get(new Random().nextInt(students.size()));
                    CourseEnrollment existingEnrollment = courseEnrollmentRepository.findByCoursesAndEnrolledStudent(course, randomStudents);
                    if (existingEnrollment == null) {
                        studentFound = true;
                        CourseEnrollment courseEnrollment = new CourseEnrollment();
                        courseEnrollment.setCourses(course).setEnrollmentDate(courseStartDate).setEnrolledStudent(randomStudents);
                        courseEnrollmentRepository.save(courseEnrollment);
                    }
                }
                String randomName = "Discount " + i;
                String randomDescription = "Description " + i;
                int randomValueDiscount = random.nextInt(50) + 10;
                int randomRemainingUses = random.nextInt(10);
                Discount discount = new Discount();
                discount.setRemainingUses(randomRemainingUses)
                        .setValueDiscount(randomValueDiscount).
                        setName(randomName).setDescription(randomDescription);
                discountRepository.save(discount);
                for (int f = 0; f < randomValueDiscount; f++) {
                    UUID randomUUID = UUID.randomUUID();
                    String randomDiscountCode = randomUUID.toString();
                    CourseDiscount courseDiscount = new CourseDiscount();
                    int n = random.nextInt(30);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.DAY_OF_MONTH, n);
                    Date redemptionDate = calendar.getTime();
                    courseDiscount
                            .setCode(randomDiscountCode)
                            .setCourse(course).setDiscount(discount).setQuantity(randomValueDiscount).setRedemptionDate(redemptionDate);
                    courseDiscountRepository.save(courseDiscount);
                }

            }
        }
    }
}