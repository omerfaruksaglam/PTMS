package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Driver;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import com.smartICT.PTMS.Services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/drivers/{ssn}")
    public Driver getDriver(@PathVariable(value ="ssn") long ssn){
        return driverService.getDriver(ssn);
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }

    @PostMapping("/drivers")
    public void createDriver(@RequestBody Driver new_driver){
        driverService.createDriver(new_driver);
    }

    @PutMapping("/drivers/{ssn}")
            public void updateDriver (@PathVariable(value = "ssn") long ssn, @RequestBody Driver driverDetails) throws ResourceNotFoundException {
        driverService.updateDriver(ssn, driverDetails);
    }

    @DeleteMapping("/drivers/{ssn}")
    public void deleteDriver(@PathVariable(value = "ssn") long ssn) {
        driverService.deleteDriver(ssn);
    }
}
