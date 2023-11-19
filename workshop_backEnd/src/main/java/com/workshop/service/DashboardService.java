package com.workshop.service;

import com.workshop.dto.DashBoardDTO.DashboardDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardService {
     DashboardDTO Dashboard();

}
