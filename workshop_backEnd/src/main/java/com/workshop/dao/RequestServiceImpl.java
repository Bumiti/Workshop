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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            if(request.getCourses()!=null){
                requestResponse.setCourseName(request.getCourses().getName());
                requestResponse.setCourseId(request.getCourses().getId());
            }
            if(request.getLocation() !=null){
                requestResponse.setLocationId(request.getLocation().getId());
                requestResponse.setLocationName(request.getLocation().getName());
            }
            requestResponse.setId(request.getId())
                    .setStatus(String.valueOf(request.getStatus()))
                    .setType(String.valueOf(request.getType()))
                    .setUserId(request.getUser().getId()).setUserName(request.getUser().getUser_name())
                    .setRegistrationDateTime(request.getCreatedDate().toLocalDateTime());
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
                default:
                    break;
            }
            return result;
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }

    @Override
    public boolean changeStatusRequest(Long request_id) {
        return false;
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
                    .setTransactionDate(LocalDateTime.now());
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
                    .setTransactionDate(LocalDateTime.now());
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
                    .setTransactionDate(LocalDateTime.now());
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
                    .setTransactionDate(LocalDateTime.now());
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
                    .setTransactionDate(LocalDateTime.now());
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
                    .setTransactionDate(LocalDateTime.now());
            transactionRepository.save(transaction);
            return "CANCEL";
        }
    }
    private String handleBuyCourseRequest(User user, RequestDTO requestDTO, Request request, Transaction transaction, PaymentMethod paymentMethod,Course course)
    {
        try{
            Optional<Course> courseOp= courseRepository.findById(requestDTO.getItem_register_id());
            if( courseOp.isPresent() && courseOp.get().isPublic())
            {
                course = courseOp.get();
                final double transactionFees = 0.03;
                request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType())).setCourses(course);
                paymentMethod.setDescription(requestDTO.getType()).setName(requestDTO.getPaymentName());
                transaction.setRequest(request).setUser(user).setPaymentMethod(paymentMethod)
                        .setAmount(requestDTO.getAmount())
                        .setType(Transaction.Type.valueOf(requestDTO.getType()))
                        .setStatus(Transaction.Status.COMPLETED)
                        .setTransactionDate(LocalDateTime.now());
                Long TeacherId = course.getTeacher().getId();
                User teacher = userRepository.findById(TeacherId).orElse(null);
                User Admin = userRepository.findByEmail("admin64@gmail.com").orElse(null);
                assert Admin != null;
                Long AdminId = Admin.getId();
                Long StudentId = user.getId();
                Double discountAmount = requestDTO.getDiscountAmount();
                Double dtoAmount=requestDTO.getAmount();
                Double AmountAfterDiscount =0.0;
                Double newBalanceForTeacher = 0.0;
                Double newBalanceForAdmin = 0.0;
                Double newBalanceForStudent = 0.0;
                if(requestDTO.getStatus().equals("payment_gateway"))
                {
                    if(discountAmount>0 && dtoAmount>discountAmount && requestDTO.getDiscountCode()!=null){
                        AmountAfterDiscount = Math.max(0, dtoAmount - discountAmount);
                        BigDecimal transactionFee = BigDecimal.valueOf(transactionFees).multiply(BigDecimal.valueOf(AmountAfterDiscount));
                        transactionFee = transactionFee.setScale(2, RoundingMode.HALF_UP);
                        newBalanceForTeacher = teacher.getBalance() + AmountAfterDiscount - transactionFee.doubleValue();
                        newBalanceForAdmin = Admin.getBalance() + transactionFee.doubleValue();
                    }
                    else{
                        Double transactionFee = transactionFees * requestDTO.getAmount();
                        newBalanceForTeacher = teacher.getBalance() + requestDTO.getAmount() - transactionFee;
                        newBalanceForAdmin = Admin.getBalance() + transactionFee;
                    }
                }else{
                    if(discountAmount>0 && dtoAmount>discountAmount && requestDTO.getDiscountCode()!=null){
                        AmountAfterDiscount = Math.max(0, dtoAmount - discountAmount);
                        BigDecimal transactionFee = BigDecimal.valueOf(transactionFees).multiply(BigDecimal.valueOf(AmountAfterDiscount));
                        transactionFee = transactionFee.setScale(2, RoundingMode.HALF_UP);
                        newBalanceForTeacher = teacher.getBalance() + AmountAfterDiscount - transactionFee.doubleValue();
                        newBalanceForAdmin = Admin.getBalance() + transactionFee.doubleValue();
                        newBalanceForStudent =user.getBalance() -dtoAmount;
                        userRepository.updateBalanceAccountById(StudentId, newBalanceForStudent);
                    }
                    else{
                        Double transactionFee = transactionFees * requestDTO.getAmount();
                        newBalanceForTeacher = teacher.getBalance() + requestDTO.getAmount() - transactionFee;
                        newBalanceForAdmin = Admin.getBalance() + transactionFee;
                        newBalanceForStudent =user.getBalance() -dtoAmount;
                        userRepository.updateBalanceAccountById(StudentId, newBalanceForStudent);
                    }
                }

                courseRepository.addStudentToCourseEnroll(requestDTO.getItem_register_id(),user.getId());
                userRepository.updateBalanceAccountById(TeacherId, newBalanceForTeacher);
                userRepository.updateBalanceAccountById(AdminId, newBalanceForAdmin);
                requestRepository.save(request);
                paymentRepository.save(paymentMethod);
                transactionRepository.save(transaction);
                courseDiscountRepository.deleteByCode(requestDTO.getDiscountCode());
                return "APPROVED";
            }
        }catch (Exception e)
        {
            return "PENDING";
        }
        return null;
    }


}
