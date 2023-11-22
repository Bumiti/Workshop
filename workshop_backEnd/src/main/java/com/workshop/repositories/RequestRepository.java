package com.workshop.repositories;

import com.workshop.model.Request;
import com.workshop.model.userModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findByCreatedDateBetween(Timestamp createdDate, Timestamp createdDate2);
}
