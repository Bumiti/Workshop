package com.workshop.dao;


import com.workshop.dto.DashBoardDTO.DashboardDTO;
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

import java.text.DecimalFormat;
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

            LocalDateTime startOfLastMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1);
            LocalDateTime endOfLastMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth()).minusDays(1);

            DecimalFormat df = new DecimalFormat("#.####");

            List<User> totalAccount = userRepository.findAll();
            List<User> totalUser = userRepository.countUsersWithUserRole();
            List<User> totalTeacher = userRepository.countUsersWithSellerRole();
//            List<User> newUserThisMonth = totalUser.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(startOfMonth)).toList();
            List<User> newUserThisMonth = userRepository.getUsersCreatedBetween(startOfMonth,endOfMonth);
            List<User> newUserLastMonth = userRepository.getUsersCreatedBetween(startOfLastMonth,endOfLastMonth);
            int checkLechSoLuongUser = (int) (newUserThisMonth.size() - newUserLastMonth.size());
            double phanTramThayDoisoluongUser = 0.0;
            if (!newUserLastMonth.isEmpty()) {
                phanTramThayDoisoluongUser = ((double) checkLechSoLuongUser / Math.abs(newUserLastMonth.size())) * 100;
            }
            phanTramThayDoisoluongUser = Double.parseDouble(df.format(phanTramThayDoisoluongUser));

            List<User> newTeacherThisMonth = userRepository.getUsersWithRolesAndRoleCreatedBetween(startOfMonth,endOfMonth,"SELLER");
            List<User> newStudentThisMonth = userRepository.getUsersWithRolesAndRoleCreatedBetween(startOfMonth,endOfMonth,"USER");

            List<User> newStudentThisYear = totalUser.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusYears(1))).toList();
            List<User> newTeacherThisYear = totalTeacher.stream().filter(user -> user.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusYears(1))).toList();

            List<Course> courseResponsesList = courseRepository.findAll();
            List<Course> newCoursesThisMonth = courseRepository.getCoursesCreatedBetween(startOfMonth,endOfMonth);
            List<Course> newCoursesLastMonth =  courseRepository.getCoursesCreatedBetween(startOfLastMonth,endOfLastMonth);
            List<Course> newCoursesThisYear= courseResponsesList.stream().filter(course -> course.getCreatedDate().toLocalDateTime().isAfter(LocalDateTime.now().minusMonths(1))).toList();

            int checkLechSoLuongCourse = (int) (newCoursesThisMonth.size() - newCoursesLastMonth.size());
            double phanTramThayDoisoluongCourse = 0.0;
            if (!newCoursesLastMonth.isEmpty()) {
                phanTramThayDoisoluongCourse = ((double) checkLechSoLuongCourse / Math.abs(newCoursesLastMonth.size())) * 100;
            }
            phanTramThayDoisoluongCourse = Double.parseDouble(df.format(phanTramThayDoisoluongCourse));

            long transactionList = transactionRepository.findAll().size();
            long successfulTransactions = transactionRepository.countSuccessfulTransactions();
            long FailedTransactions = transactionRepository.countFailedTransactions();


            long TotalRevenue = transactionRepository.getTotalAmountOfCompletedBuyCourseTransactions();
            long CoursesRevenueThisMonth = transactionRepository.getTotalAmountOfCompletedBuyCourseTransactionsInMount(startOfMonth,endOfMonth);
            long CoursesRevenueLastMonth = transactionRepository.getTotalAmountOfCompletedBuyCourseTransactionsInMount(startOfLastMonth, endOfLastMonth);
            int checkLechDoanhThu = (int) (CoursesRevenueThisMonth - CoursesRevenueLastMonth);
            double phanTramThayDoi = 0.0;
            if (CoursesRevenueLastMonth != 0) {
                phanTramThayDoi = ((double) checkLechDoanhThu / Math.abs(CoursesRevenueLastMonth)) * 100;
            }

            phanTramThayDoi = Double.parseDouble(df.format(phanTramThayDoi));

            List<UserBanking> userBankings = userBankRepository.findAll();
            Dashboard
                    //account//
                    .setTotalAccount(totalAccount.size())
                    .setTotalTeacher(totalTeacher.size()).setNewTeacherThisMonth(newTeacherThisMonth.size()).setNewTeacherThisYear(newTeacherThisYear.size())
                    .setTotalStudent(totalUser.size()).setNewStudentThisMonth(newStudentThisMonth.size()).setNewStudentThisYear(newStudentThisYear.size())
                    .setRatioUser(phanTramThayDoisoluongUser)
                    //account//

                    //course//
                    .setTotalCourses(courseResponsesList.size()).setNewCoursesThisMonth(newCoursesThisMonth.size()).setNewCoursesThisYear(newCoursesThisYear.size())
                    .setRatioCourse(phanTramThayDoisoluongCourse)
                    //course//

                    //Revenue//
                    .setTotalCoursesPricing((int) TotalRevenue).setCoursesPricingThisMonth((int) CoursesRevenueThisMonth)
                    .setTotalRevenue((int) ((int) TotalRevenue*0.03)).setRevenueThisMonth((int) CoursesRevenueThisMonth*0.03)
                    .setRatioRevenue(phanTramThayDoi)
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
