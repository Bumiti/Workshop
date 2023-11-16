package com.workshop.repositories.User;

import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email =:email and u.isEnable =true")
    Optional<User> findByEmail(String email);
    @Query("select u from User u where u.email =:email and u.isEnable =true")
    User getUserEditByMail(String email);
    @Query("select u from User u left join fetch u.userAddresses ua where u.email = :email and u.isEnable = true")
    Optional<User> findByEmailWithAddresses(String email);
    @Query(value = "SELECT u FROM User u " +
            "JOIN FETCH u.roles r " +
            "WHERE r.name = ?1")
    List<User> findUsersByRoleName(String roleName);
    @Modifying
    @Query(value = "select u.* from users u join public.users_role ur on u.id = ur.user_id\n" +
            "join public.roles r on ur.roles_id = r.id\n" +
            "where r.name != 'ADMIN'", nativeQuery = true)
    List<User> findUsersNonAdmin();

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.isEnable = CASE WHEN u.isEnable = true THEN false ELSE true END WHERE u.id = :id")
    int chanceStatusAccountById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.password =:NewPassword  WHERE u.id = :id")
    void chancePasswordAccountById(Long id, String NewPassword);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.balance =:NewBalance  WHERE u.id = :id")
    void updateBalanceAccountById(Long id, Double NewBalance);
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.phoneNumber = :#{#user.phoneNumber},u.user_name =:#{#user.user_name},u.full_name =:#{#user.full_name},u.image_url =:#{#user.image_url} WHERE u.id = :#{#user.id}")
    void updateUser(@Param("user") User user);


}
