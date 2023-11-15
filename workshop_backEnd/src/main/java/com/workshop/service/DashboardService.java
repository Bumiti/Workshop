package com.workshop.service;

import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.dto.DashBoardDTO.DashboardDTO;
import com.workshop.dto.DashBoardDTO.TransactionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardService {
     DashboardDTO Dashboard();

    public List<CourseResponses> sortCoursesByWeek(List<CourseResponses> courseResponses);
    // Sort transactions by month
    public List<CourseResponses> sortCoursesByMonth(List<CourseResponses> courseResponses);

    // Sort transactions by year
    public List<CourseResponses> sortCoursesByYear(List<CourseResponses> courseResponses);

    // Sort transactions by week
    public List<TransactionDTO> sortTransactionsByWeek(List<TransactionDTO> transactions);
    // Sort transactions by month
    public List<TransactionDTO> sortTransactionsByMonth(List<TransactionDTO> transactions);

    // Sort transactions by year
    public List<TransactionDTO> sortTransactionsByYear(List<TransactionDTO> transactions);

    // Sort transactions by type
    public List<TransactionDTO> sortTransactionsByType(List<TransactionDTO> transactions, String type);


}
