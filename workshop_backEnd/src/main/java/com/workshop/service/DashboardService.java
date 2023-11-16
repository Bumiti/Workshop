package com.workshop.service;

import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.dto.DashBoardDTO.DashboardDTO;
import com.workshop.dto.DashBoardDTO.TransactionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardService {
     DashboardDTO Dashboard();


}
