package com.workshop.reposetory;

import com.workshop.model.userModel.User;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Query("select u from User u where u.email =:email and u.isEnable =true")
    Optional<User> findByEmail(String email);
    @Transactional
    @Modifying
    @Query(value = "SELECT u.* FROM users u " +
            "JOIN users_role ur ON u.id = ur.user_id " +
            "JOIN roles r ON ur.roles_id = r.id " +
            "WHERE r.name = ?1", nativeQuery = true)
    List<User> findUsersByRoleName(String roleName);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnable = CASE WHEN u.isEnable = true THEN false ELSE true END WHERE u.id = :id")
    int chanceStatusAccountById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password =?2  WHERE u.id = ?1")
    int chancePasswordAccountById(Long id,String  NewPassword);

//    @Modifying
//    @Query( "INSERT INTO users (user_name, full_name, image_url, phone_number, email, is_enable) " +
//            "VALUES (:#{#userExit.user_name}, :#{#userExit.full_name}, :#{#userExit.image_url}, :#{#userExit.phoneNumber}, :#{#userExit.email}, true)")
//    void saveUser(@Param("userExit") User userExit);

}
