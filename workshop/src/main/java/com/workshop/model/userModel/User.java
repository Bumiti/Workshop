package com.workshop.model.userModel;

import com.workshop.model.*;
import com.workshop.model.storeModel.Store;
import com.workshop.model.storeModel.StoreFollowers;
import com.workshop.model.storeModel.StoreReports;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User extends BaseModel implements UserDetails  {

    @Column(name="full_name")
    private String full_name;
    @Column(name="user_name")
    private String user_name;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserAddresses> userAddresses = new HashSet<>();

    @OneToMany(mappedBy = "reportedUser", fetch = FetchType.LAZY)
    private Set<Report> reportedReports = new HashSet<>();

    @OneToMany(mappedBy = "reporterUser", fetch = FetchType.LAZY)
    private Set<Report> reporterReports= new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<PaymentTransactions> paymentTrans= new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Notifications> notifications= new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Store> stores= new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<StoreFollowers> storeFollowers = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<StoreReports> storeReports = new HashSet<>();

    @ManyToMany
    @JoinTable(name="users_role",
            joinColumns = @JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name="Roles_id"))
    private Set<Roles> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name="users_coupons",
            joinColumns = @JoinColumn(name="User_id"),
            inverseJoinColumns = @JoinColumn(name="Coupoun_id"))
    private Set<Coupons> coupons = new HashSet<>();



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
