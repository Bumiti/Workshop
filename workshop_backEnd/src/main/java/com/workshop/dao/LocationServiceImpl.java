package com.workshop.dao;

import com.workshop.model.Location;
import com.workshop.reposetory.LocationRepository;
import com.workshop.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Override
    public boolean AddLocation(Location location) {
       try{
           locationRepository.save(location);
           return true;
       }catch (Exception ex){

           return false;
       }
    }
}
