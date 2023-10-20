package com.workshop.reposetory;


import com.workshop.model.userModel.UserAddresses;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface UserAddressRepository extends JpaRepository<UserAddresses,Long> {

}
