package com.workshop.repositories;

import com.workshop.model.userModel.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Roles,Long>
{
    Roles findByName(String name);
}
