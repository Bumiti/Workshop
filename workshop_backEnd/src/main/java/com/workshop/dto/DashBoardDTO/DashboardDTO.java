package com.workshop.dto.DashBoardDTO;

import com.workshop.dto.CourseDTO.CourseResponses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DashboardDTO {
    //Account//
    private int totalAccount = 0 ;
    private int totalStudent = 0 ;
    private int newStudentThisMonth = 0 ;
    private int newStudentThisYear = 0 ;
    private int totalTeacher = 0 ;
    private int newTeacherThisMonth = 0 ;
    private int newTeacherThisYear = 0 ;
    //Account//

    //Course//
    private int totalCourses = 0 ;
    private int newCoursesThisMonth = 0 ;
    private int newCoursesThisYear = 0;
    //Course//

    private int totalRevenue = 0 ;/* Tổng doanh thu từ cơ sở dữ liệu */;
    private int revenueThisMonth = 0 ; /* Doanh thu trong tháng này */;

    private int totalRequests = 0 ; /* Tổng số lượng yêu cầu từ cơ sở dữ liệu */;
    private int requestsThisMonth = 0 ;/* Số lượng yêu cầu trong tháng này */;

    private int totalLocations = 0 ;/* Số lượng địa điểm từ cơ sở dữ liệu */;
    private int coursesPerLocation = 0 ; /* Số lượng khóa học đăng ký tại mỗi địa điểm */;

    private int totalDiscounts = 0 ; /* Tổng số lượng mã giảm giá từ cơ sở dữ liệu */;
    private int remainingDiscounts = 0 ;/* Số lượng mã giảm giá còn lại */;

    private int totalTransactions = 0 ; /* Tổng số lượng giao dịch từ cơ sở dữ liệu */;
    private int successfulTransactions = 0 ;/* Số lượng giao dịch thành công */;
    private int failedTransactions = 0 ;/* Số lượng giao dịch thất bại */;

    private int totalBankAccounts = 0 ;

    private int totalEmailVerifications = 0 ;
    private  int activatedAccounts = 0 ;


    private int chartData = 0 ;/* Dữ liệu cho biểu đồ hoặc đồ thị */;



}
