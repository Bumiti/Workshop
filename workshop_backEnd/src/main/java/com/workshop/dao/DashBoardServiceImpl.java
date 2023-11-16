package com.workshop.dao;

import com.workshop.dto.CourseDTO.CourseResponses;
import com.workshop.dto.DashBoardDTO.DashboardDTO;
import com.workshop.dto.DashBoardDTO.TransactionDTO;
import com.workshop.model.Discount;
import com.workshop.model.Transaction;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.UserBanking;
import com.workshop.repositories.Course.CourseRepository;
import com.workshop.repositories.DiscountRepository;
import com.workshop.repositories.TransactionRepository;
import com.workshop.repositories.User.UserBankRepository;
import com.workshop.repositories.User.UserRepository;
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
    private final CourseRepository courseRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private  final DiscountRepository discountRepository;
    private final UserBankRepository userBankRepository;
    @Override
    public DashboardDTO Dashboard() {
       try {
           DashboardDTO Dashboard = new DashboardDTO();
           List<User>totalAccount = userRepository.findAll();
           List<User> totalUser = userRepository.countUsersWithUserRole();
           List<User>  totalTeacher = userRepository.countUsersWithSellerRole();

           List<User> newUserThisMonth = totalUser.stream()
                   .filter(user ->user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                   .toList();
           List<User> newTeacherThisMonth = totalTeacher.stream()
                   .filter(user ->user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                   .toList();
           List<Course> courseResponsesList =courseRepository.findAll();
           List<Course> newCoursesThisMonth = courseResponsesList.stream()
                   .filter(course ->course.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                   .toList();

           long transactionList = transactionRepository.findAll().size();
            long successfulTransactions = transactionRepository.countSuccessfulTransactions();
           long FailedTransactions = transactionRepository.countFailedTransactions();
           List<Transaction> findCompletedBuyCourseTransactions =transactionRepository.findCompletedBuyCourseTransactions();
           List<Transaction> RevenueThisMonth = findCompletedBuyCourseTransactions.stream()
                   .filter(trans ->trans.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1)))
                   .toList();
           List<UserBanking> userBankings = userBankRepository.findAll();
           Dashboard
                   .setTotalAccount( totalAccount.size())
                   .setTotalTeacher( totalTeacher.size()).setNewTeacherThisMonth(newTeacherThisMonth.size())
                   .setTotalStudent( totalUser.size()).setNewStudentThisMonth(newUserThisMonth.size())

                   .setTotalCourses(courseResponsesList.size())
                   .setNewCoursesThisMonth(newCoursesThisMonth.size())

                   .setTotalDiscounts(discountRepository.findAll()
                    .size()).setTotalBankAccounts(userBankings.size())

                   .setTotalRevenue(findCompletedBuyCourseTransactions.size())
                   .setRevenueThisMonth(RevenueThisMonth.size())
                   .setTotalTransactions((int) transactionList)
                   .setSuccessfulTransactions((int) successfulTransactions).setFailedTransactions((int) FailedTransactions);

           return Dashboard;
       }catch (Exception e){
           throw e;
       }
    }

}
