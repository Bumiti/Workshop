package com.workshop.dao;

import com.workshop.dto.RequestDTO.RequestDTO;
import com.workshop.dto.RequestResponse;
import com.workshop.model.PaymentMethod;
import com.workshop.model.Request;
import com.workshop.model.Transaction;
import com.workshop.model.userModel.User;
import com.workshop.repositories.PaymentRepository;
import com.workshop.repositories.RequestRepository;
import com.workshop.repositories.TransactionRepository;
import com.workshop.repositories.User.UserRepository;
import com.workshop.service.RequestService;
import com.workshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Request createRequestOptions(RequestDTO requestDTO) {
        try {
            User user = userService.getCurrentUserDetails();
            Request request = new Request();
            Transaction transaction = new Transaction();
            PaymentMethod paymentMethod = new PaymentMethod();
            switch (requestDTO.getType()) {
                case "DEPOSIT":
                 if(requestDTO.getPaymentStatus().equals("success") && requestDTO.getAmount()>0){
                     request.setUser(user).setStatus(Request.RequestStatus.APPROVED).setType(Request.RequestType.valueOf(requestDTO.getType()));
                     paymentMethod.setDescription(requestDTO.getType().toString()).setName(requestDTO.getPaymentName());
                     Double newBalance = user.getBalance() + requestDTO.getAmount();
                     Long id = user.getId();
                     paymentRepository.save(paymentMethod);
                     userRepository.updateBalanceAccountById(id,newBalance);
                     requestRepository.save(request);
                     transaction.setRequest(request).setUser(user)
                             .setAmount(requestDTO.getAmount())
                             .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                             .setStatus(Transaction.Status.COMPLETED)
                             .setTransactionDate(requestDTO.getRegistrationDateTime());
                     transactionRepository.save(transaction);
                 }else if(requestDTO.getPaymentStatus().equals("pending") && requestDTO.getAmount()>0){
                     request.setUser(user).setStatus(Request.RequestStatus.PENDING).setType(Request.RequestType.valueOf(requestDTO.getType()));
                     paymentMethod.setDescription(requestDTO.getType().toString()).setName(requestDTO.getPaymentName());
                     paymentRepository.save(paymentMethod);
                     requestRepository.save(request);
                     transaction.setRequest(request).setUser(user)
                             .setAmount(requestDTO.getAmount())
                             .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                             .setStatus(Transaction.Status.PENDING)
                             .setTransactionDate(requestDTO.getRegistrationDateTime());
                     transactionRepository.save(transaction);
                 }else{
                     request.setUser(user).setStatus(Request.RequestStatus.REJECTED).setType(Request.RequestType.valueOf(requestDTO.getType()));
                     paymentMethod.setDescription(requestDTO.getType().toString()).setName(requestDTO.getPaymentName());
                     paymentRepository.save(paymentMethod);
                     requestRepository.save(request);
                     transaction.setRequest(request).setUser(user)
                             .setAmount(requestDTO.getAmount())
                             .setType(Transaction.Type.valueOf(requestDTO.getType().toString()))
                             .setStatus(Transaction.Status.FAILED)
                             .setTransactionDate(requestDTO.getRegistrationDateTime());
                     transactionRepository.save(transaction);
                 }
                    break;
                case "BUY_COURSE":
                    if(requestDTO.getPaymentStatus().equals("balance") && requestDTO.getAmount()>0){

                    }else if(requestDTO.getPaymentStatus().equals("payment_gateway") && requestDTO.getAmount()>0)
                    {

                    }else{

                    }

                    break;
                case "BUY_WORKSHOP":


                    break;
                case "WITHDRAW":
                    // Handle withdraw-specific logic

                    break;
                case "REGISTER_COURSE_ONLINE":
                    // Handle course registration online-specific logic

                    break;
                case "REGISTER_COURSE_OFFLINE":
                    // Handle course registration offline-specific logic

                    break;
                // Add other cases as needed
                default:
                    // Handle default case if necessary
                    break;
            }
            return request;
        } catch (Exception e) {
            // Handle exceptions
            return null;
        }
    }
}
