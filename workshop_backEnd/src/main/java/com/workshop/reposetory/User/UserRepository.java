package com.workshop.reposetory.User;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email =:email and u.isEnable =true")
    Optional<User> findByEmail(String email);
    @Query("select u from User u left join fetch u.userAddresses ua where u.email = :email and u.isEnable = true")
    Optional<User> findByEmailWithAddresses(String email);

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
    @Query("UPDATE User u SET u.password =:NewPassword  WHERE u.id = :id")
    void chancePasswordAccountById(Long id,String NewPassword);


}
