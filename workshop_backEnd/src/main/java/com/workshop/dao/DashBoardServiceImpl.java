package com.workshop.dao;


import com.workshop.dto.DashBoardDTO.DashboardDTO;
import com.workshop.model.Transaction;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.model.userModel.UserBanking;
import com.workshop.repositories.Course.CourseRepository;
import com.workshop.repositories.DiscountRepository;
import com.workshop.repositories.TransactionRepository;
import com.workshop.repositories.User.UserBankRepository;
import com.workshop.repositories.User.UserRepository;
import com.workshop.service.DashboardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DashBoardServiceImpl implements DashboardService {
    private final CourseRepository courseRepository;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;
    private final UserBankRepository userBankRepository;

    @Override
    public DashboardDTO Dashboard() {
        try {
            DashboardDTO Dashboard = new DashboardDTO();

            LocalDateTime startOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
            LocalDateTime endOfMonth = LocalDateTime.now().with(TemporalAdjusters.lastDayOfMonth());

            List<User> totalAccount = userRepository.findAll();
            List<User> totalUser = userRepository.countUsersWithUserRole();
            List<User> totalTeacher = userRepository.countUsersWithSellerRole();
            List<User> newUserThisMonth = totalUser.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(startOfMonth)).toList();
            List<User> newUserThisYear = totalUser.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusYears(1))).toList();
            List<User> newTeacherThisMonth = totalTeacher.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(startOfMonth)).toList();
            List<User> newTeacherThisYear = totalTeacher.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusYears(1))).toList();

            List<Course> courseResponsesList = courseRepository.findAll();
            List<Course> newCoursesThisMonth = courseResponsesList.stream().filter(course -> course.getCreatedDate().toLocalDateTime().isAfter(startOfMonth)).toList();
            List<Course> newCoursesThisYear= courseResponsesList.stream().filter(course -> course.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1))).toList();

            long transactionList = transactionRepository.findAll().size();
            long successfulTransactions = transactionRepository.countSuccessfulTransactions();
            long FailedTransactions = transactionRepository.countFailedTransactions();

            long TotalRevenue = transactionRepository.getTotalAmountOfCompletedBuyCourseTransactions();
            long CoursesRevenueThisMonth = transactionRepository.getTotalAmountOfCompletedBuyCourseTransactionsInMount(startOfMonth,endOfMonth);


            List<UserBanking> userBankings = userBankRepository.findAll();
            Dashboard
                    //account//
                    .setTotalAccount(totalAccount.size())
                    .setTotalTeacher(totalTeacher.size()).setNewTeacherThisMonth(newTeacherThisMonth.size()).setNewTeacherThisYear(newTeacherThisYear.size())
                    .setTotalStudent(totalUser.size()).setNewStudentThisMonth(newUserThisMonth.size()).setNewStudentThisYear(newUserThisYear.size())
                    //account//

                    //course//
                    .setTotalCourses(courseResponsesList.size()).setNewCoursesThisMonth(newCoursesThisMonth.size()).setNewCoursesThisYear(newCoursesThisYear.size())
                    //course//

                    //Revenue//
                    .setTotalCoursesPricing((int) TotalRevenue).setCoursesPricingThisMonth((int) CoursesRevenueThisMonth)
                    .setTotalRevenue((int) ((int) TotalRevenue*0.03)).setRevenueThisMonth((int) CoursesRevenueThisMonth*0.03)

                    .setRatioRevenue(1)
                    //Revenue//

                    .setTotalDiscounts(discountRepository.findAll().size())
                    .setTotalBankAccounts(userBankings.size())

                    .setTotalTransactions((int) transactionList)


                    .setSuccessfulTransactions((int) successfulTransactions).setFailedTransactions((int) FailedTransactions);

            return Dashboard;
        } catch (Exception e) {
            throw e;
        }
    }

}
