package com.workshop.dao;

import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.dto.DashBoardDTO.DashboardDTO;
import com.workshop.dto.DashBoardDTO.TransactionDTO;
import com.workshop.repositories.TransactionRepository;
import com.workshop.service.AdminService;
import com.workshop.service.CourseService;
import com.workshop.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashboardService {
    private final CourseService courseService;
    private final TransactionRepository transactionRepository;
    private final AdminService adminService;



    @Override
    public DashboardDTO Dashboard() {
       try {
           DashboardDTO Dashboard = new DashboardDTO();
           List<CourseResponses> courseResponsesList =adminService.listCourse();
           Dashboard.setWeeklyCourseResponses(courseResponsesList).setMonthlyCourseResponses(courseResponsesList).setYearlyCourseResponses(courseResponsesList);
           return Dashboard;
       }catch (Exception e){
           throw e;
       }
    }

    @Override
    public List<CourseResponses> sortCoursesByWeek(List<CourseResponses> courseResponses) {
        return courseResponses.stream()
                .filter(courseResponse -> courseResponse.getStartDate().toLocalDateTime().isAfter(LocalDateTime.now().minusWeeks(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponses> sortCoursesByMonth(List<CourseResponses> courseResponses) {
        return courseResponses.stream()
                .filter(courseResponse -> courseResponse.getStartDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponses> sortCoursesByYear(List<CourseResponses> courseResponses) {
        return courseResponses.stream()
                .filter(courseResponse -> courseResponse.getStartDate().toLocalDateTime().isAfter(LocalDateTime.now().minusYears(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> sortTransactionsByWeek(List<TransactionDTO> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionDate().isAfter(LocalDateTime.now().minusWeeks(1)))
                .collect(Collectors.toList());
    }
    @Override
    public List<TransactionDTO> sortTransactionsByMonth(List<TransactionDTO> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionDate().isAfter(LocalDateTime.now().minusMonths(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> sortTransactionsByYear(List<TransactionDTO> transactions) {
        return transactions.stream()
                .filter(transaction -> transaction.getTransactionDate().isAfter(LocalDateTime.now().minusYears(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> sortTransactionsByType(List<TransactionDTO> transactions, String type) {
        return transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
    private LocalDateTime parseDateString(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateString, formatter);
    }
}
