package com.workshop.dao;

import com.workshop.dto.RequestResponse;
import com.workshop.model.Request;
import com.workshop.repositories.RequestRepository;
import com.workshop.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
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
}
