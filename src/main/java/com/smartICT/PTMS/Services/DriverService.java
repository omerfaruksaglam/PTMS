package com.smartICT.PTMS.Services;

import com.smartICT.PTMS.DataAccessLayer.DriverRepository;
import com.smartICT.PTMS.Entities.Driver;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Service
public class DriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriver(@PathVariable(value ="ssn") long ssn){
        return driverRepository.findById(ssn).get();
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public void createDriver(@RequestBody Driver new_driver){
        driverRepository.save(new_driver);
    }

    public ResponseEntity<Driver> updateDriver (@PathVariable(value = "ssn") long ssn, @RequestBody Driver driverDetails) throws ResourceNotFoundException {
        Driver updatedDriver = driverRepository.findById(ssn).orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id : " + ssn));

        updatedDriver.setSsn(driverDetails.getSsn());
        updatedDriver.setFirstname(driverDetails.getFirstname());
        updatedDriver.setLastname(driverDetails.getLastname());
        updatedDriver.setDob(driverDetails.getDob());
        updatedDriver.setEmail(driverDetails.getEmail());
        updatedDriver.setPhone(driverDetails.getPhone());

        return ResponseEntity.ok(updatedDriver);
    }

    public Map<String ,Boolean> deleteDriver(@PathVariable(value = "ssn") long ssn) {
        Driver driver = driverRepository.findById(ssn).orElseThrow(() ->new ResolutionException("Person not found for this id  :" + ssn ));
        driverRepository.delete(driver);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
