package com.workshop.model.userModel;

import com.workshop.model.*;

import com.workshop.model.courseModel.Course;
import com.workshop.model.courseModel.CourseEnrollment;
import com.workshop.model.workshopModel.WorkshopEnrollment;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Table(name = "users")
public class User extends BaseModel implements UserDetails  {

    @Column(name="full_name")
    private String full_name;
    @Column(name="user_name")
    private String user_name;

    @Column(name="email")
    @NaturalId(mutable = true)
    private String email;

    @Column(name="password")
    private String password;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="address")
    private String address;
    @Column(name="isEnable")
    private boolean isEnable = false;
    @Column(name="image_url")
    private String image_url;
    @OneToOne(mappedBy = "user")
    private VerificationToken verificationToken;
    // Quan hệ một nhiều với khóa học đã tạo bởi giáo viên
    @OneToMany(mappedBy = "teacher")
    private List<Course> teacherCourses;

    // Quan hệ một nhiều với khóa học và buổi workshop đã đăng ký
    @OneToMany(mappedBy = "enrolledStudent")
    private List<CourseEnrollment> enrolledCourses;

    @OneToMany(mappedBy = "enrolledStudent")
    private List<WorkshopEnrollment> enrolledWorkshops;

    // Quan hệ một nhiều với danh sách ưu đãi
    @OneToMany(mappedBy = "user")
    private List<Discount> discounts;


    @ManyToMany
    @JoinTable(name="users_role",
            joinColumns = @JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name="Roles_id"))
    private Set<Roles> roles = new HashSet<>();




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        roles.stream().forEach(i->authority.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority((authority.toString())));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
