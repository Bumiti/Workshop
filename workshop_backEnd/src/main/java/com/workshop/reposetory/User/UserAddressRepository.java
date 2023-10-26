package com.workshop.reposetory.User;


import com.workshop.model.courseModel.CourseLocation;
import com.workshop.model.userModel.UserAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserAddressRepository extends JpaRepository<UserAddresses,Long> {


    @Transactional
    @Modifying
    @Query("UPDATE UserAddresses u SET u.Address = :#{#userAddresses.address}, " +
            "u.City = :#{#userAddresses.city}, u.State = :#{#userAddresses.state}, u.PostalCode = :#{#userAddresses.postalCode} WHERE u.id = :Id")
    void updateUserAddressById(@Param("Id") Long Id, @Param("userAddresses") UserAddresses userAddresses);

}
