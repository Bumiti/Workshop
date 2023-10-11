package com.workshop.reposetory;

import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.email =:email and u.isEnable =true")
    Optional<User> findByEmail(String email);


}
