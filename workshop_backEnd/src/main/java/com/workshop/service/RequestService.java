package com.workshop.service;
import com.workshop.dto.RequestResponse;
import com.workshop.model.Request;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RequestService {
    List<RequestResponse> ListRequest();
}
