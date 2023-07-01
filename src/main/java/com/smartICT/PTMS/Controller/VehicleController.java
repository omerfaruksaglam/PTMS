package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Vehicle;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import com.smartICT.PTMS.Services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @GetMapping("/vehicles/{plate}")
    public Vehicle getVehicle(@PathVariable(value = "plate") String plate) {return vehicleService.getVehicle(plate);}

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/vehicles")
    public void creteVehicle(@RequestBody Vehicle new_vehicle) {
        vehicleService.createVehicle(new_vehicle);
    }

    @PutMapping("/vehicles/{plate}")
    public void updateVehicle(@PathVariable(value = "plate") String plate, @RequestBody
    Vehicle vehicleDetails) throws ResourceNotFoundException {
        vehicleService.updateVehicle(plate, vehicleDetails);
    }

    @DeleteMapping("/vehicles/{plate}")
    public void deleteVehicle(@PathVariable(value = "plate") String plate) throws Exception{
        vehicleService.deleteVehicle(plate);
    }
}
