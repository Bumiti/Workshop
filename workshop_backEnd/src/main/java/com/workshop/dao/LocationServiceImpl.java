package com.workshop.dao;

import com.workshop.model.Location;
import com.workshop.reposetory.LocationRepository;
import com.workshop.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    @Override
    public void AddLocation(Location location) {
       try{
           locationRepository.save(location);
       }catch (Exception ex){
       }
    }
}
