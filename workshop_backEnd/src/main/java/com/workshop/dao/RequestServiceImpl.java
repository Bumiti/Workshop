package com.workshop.dao;

import com.workshop.dto.RequestDTO.RequestDTO;
import com.workshop.dto.RequestResponse;
import com.workshop.model.*;
import com.workshop.model.courseModel.Course;
import com.workshop.model.userModel.User;
import com.workshop.repositories.*;
import com.workshop.repositories.Course.CourseDiscountRepository;
import com.workshop.repositories.Course.CourseRepository;
import com.workshop.repositories.User.UserRepository;
import com.workshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final PaymentRepository paymentRepository;
    private final CourseRepository courseRepository;
    private final CourseDiscountRepository courseDiscountRepository;
    @Override
    public List<RequestResponse> ListRequest() {
        List<Request> requestList =  requestRepository.findAll();
        List<RequestResponse> requestResponseList =  new ArrayList<>();
        for(Request request : requestList)
        {
            RequestResponse requestResponse = new RequestResponse();
            if(request.getCourse()!=null){
                requestResponse.setCourseName(request.getCourse().getName());
                requestResponse.setCourseId(request.getCourse().getId());
            }
            if(request.getWorkshop() !=null){
                requestResponse.setWorkshopId(request.getWorkshop().getId());
                requestResponse.setWorkshopName(request.getWorkshop().getName());
            }
            if(request.getLocation() !=null){
                requestResponse.setLocationId(request.getLocation().getId());
                requestResponse.setLocationName(request.getLocation().getName());
            }
            requestResponse.setId(request.getId())
                    .setStatus(String.valueOf(request.getStatus()))
                    .setType(String.valueOf(request.getType()))
                    .setUserId(request.getUser().getId()).setUserName(request.getUser().getUser_name());
            requestResponseList.add(requestResponse);
        }
       return requestResponseList;
    }
    @Override
    public String createRequestOptions(RequestDTO requestDTO) {
        try {
            String result = "";
            User user = userService.getCurrentUserDetails();
            Request request = new Request();
            Transaction transaction = new Transaction();
            PaymentMethod paymentMethod = new PaymentMethod();
            Course course = new Course();
            switch (requestDTO.getType()) {
                case "DEPOSIT":
                    result = (handleDepositRequest(user, requestDTO, request, transaction, paymentMethod));
                    break;
                case "WITHDRAW":
                    result =(handleWithDrawRequest(user, requestDTO, request, transaction, paymentMethod));
                break;
                case "BUY_COURSE":
                    result = (handleBuyCourseRequest(user, requestDTO, request, transaction, paymentMethod,course));
                    break;
                case "BUY_WORKSHOP":
                    break;

                case "REGISTER_COURSE_ONLINE":
                    break;
                case "REGISTER_COURSE_OFFLINE":
                    break;
                default:
                    break;
            }
            return result;
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }
    private String handleDepositRequest(User user, RequestDTO requestDTO, Request request, Transaction transaction, PaymentMethod paymentMethod) {
        if(requestDTO.getPaymentStatus().equals("success") && requestDTO.getAmount()>0){
            request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            Double newBalance = user.getBalance() + requestDTO.getAmount();
            Long id = user.getId();
            paymentRepository.save(paymentMethod);
            userRepository.updateBalanceAccountById(id,newBalance);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.COMPLETED)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "APPROVED";
        }else if(requestDTO.getPaymentStatus().equals("pending") && requestDTO.getAmount()>0){
            request.setUser(user).setStatus(Request.RequestStatus.PENDING).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            paymentRepository.save(paymentMethod);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.PENDING)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "PENDING";
        }else{
            request.setUser(user).setStatus(Request.RequestStatus.REJECTED).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            paymentRepository.save(paymentMethod);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.FAILED)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "REJECTED";
        }
    };
    private String handleWithDrawRequest(User user, RequestDTO requestDTO, Request request, Transaction transaction, PaymentMethod paymentMethod){
        if(requestDTO.getAmount() < user.getBalance() && (user.getBalance() - requestDTO.getAmount()) >10)
        {
            request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            Double newBalance = user.getBalance() - requestDTO.getAmount();
            Long id = user.getId();
            paymentRepository.save(paymentMethod);
            userRepository.updateBalanceAccountById(id,newBalance);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.COMPLETED)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "APPROVED";
        }else if(requestDTO.getAmount() < user.getBalance() && (user.getBalance() - requestDTO.getAmount()) <10){
            request.setUser(user).setStatus(Request.RequestStatus.REJECTED).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            paymentRepository.save(paymentMethod);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.CANCELED)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "REJECTED";
        }else{
            request.setUser(user).setStatus(Request.RequestStatus.CANCEL).setType(Request.RequestType.valueOf(requestDTO.getType()));
            paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
            paymentRepository.save(paymentMethod);
            requestRepository.save(request);
            transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                    .setAmount(requestDTO.getAmount())
                    .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                    .setStatus(Transaction.Status.FAILED)
                    .setTransactionDate(requestDTO.getRegistrationDateTime());
            transactionRepository.save(transaction);
            return "CANCEL";
        }
    }
    private String handleBuyCourseRequest(User user, RequestDTO requestDTO, Request request, Transaction transaction, PaymentMethod paymentMethod,Course course)
    {
        course = courseRepository.findById(requestDTO.getItem_register_id()).get();
        if(requestDTO.getPaymentStatus().equals("success") && requestDTO.getStatus().equals("payment_gateway"))
        {
           try{
               if(requestDTO.getDiscountAmount()>0 && requestDTO.getAmount()>requestDTO.getDiscountAmount() && requestDTO.getDiscountCode()!=null)
               {
                   request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType()));
                   paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
                   transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                           .setAmount(requestDTO.getAmount())
                           .setType(Transaction.Type.valueOf(requestDTO.getType()))
                           .setStatus(Transaction.Status.COMPLETED)
                           .setTransactionDate(requestDTO.getRegistrationDateTime());
                   courseRepository.addStudentToCourseEnroll(requestDTO.getItem_register_id(),user.getId());
                   Long TeacherId = course.getTeacher().getId();
                   User teacher = userRepository.findById(TeacherId).get();
                   User Admin = userRepository.findByEmail("admin64@gmail.com").get();
                   Long AdminId = Admin.getId();
//                   Double balanceAfterDiscount = requestDTO.getAmount()-requestDTO.getDiscountAmount();
                   Double balanceAfterDiscount = Math.max(0, requestDTO.getAmount() - requestDTO.getDiscountAmount());
                   BigDecimal transactionFee = BigDecimal.valueOf(0.03).multiply(BigDecimal.valueOf(balanceAfterDiscount));
                   transactionFee = transactionFee.setScale(2, RoundingMode.HALF_UP);
//                   Double newBalanceForTeacher = teacher.getBalance() + balanceAfterDiscount - transactionFee;
//                   Double newBalanceForAdmin = Admin.getBalance() + transactionFee;
                   Double newBalanceForTeacher = teacher.getBalance() + balanceAfterDiscount - transactionFee.doubleValue();
                   Double newBalanceForAdmin = Admin.getBalance() + transactionFee.doubleValue();
                   userRepository.updateBalanceAccountById(TeacherId, newBalanceForTeacher);
                   userRepository.updateBalanceAccountById(AdminId, newBalanceForAdmin);
                   requestRepository.save(request);
                   paymentRepository.save(paymentMethod);
                   transactionRepository.save(transaction);
                   courseDiscountRepository.deleteByCode(requestDTO.getDiscountCode());
                   return "APPROVED";
               }
               //no discount
               else
               {
                   request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType()));
                   paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
                   transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                           .setAmount(requestDTO.getAmount())
                           .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                           .setStatus(Transaction.Status.COMPLETED)
                           .setTransactionDate(requestDTO.getRegistrationDateTime());
                   courseRepository.addStudentToCourseEnroll(requestDTO.getItem_register_id(),user.getId());
                   Long TeacherId = course.getTeacher().getId();
                   User teacher = userRepository.findById(TeacherId).get();
                   User Admin = userRepository.findByEmail("admin64@gmail.com").get();
                   Long AdminId = Admin.getId();
                   Double transactionFee = 0.03 * requestDTO.getAmount();
                   Double newBalanceForTeacher = teacher.getBalance() + requestDTO.getAmount() - transactionFee;
                   userRepository.updateBalanceAccountById(TeacherId, newBalanceForTeacher);
                   Double newBalanceForAdmin = Admin.getBalance() + transactionFee;
                   userRepository.updateBalanceAccountById(AdminId, newBalanceForAdmin);
                   requestRepository.save(request);
                   paymentRepository.save(paymentMethod);
                   transactionRepository.save(transaction);
                   return "APPROVED";
               }
           }catch (Exception e){
               throw e;
           }

        }else if(requestDTO.getPaymentStatus().equals("success") && requestDTO.getStatus().equals("balance")){

        }else{

        }
        return null;
    }
}
