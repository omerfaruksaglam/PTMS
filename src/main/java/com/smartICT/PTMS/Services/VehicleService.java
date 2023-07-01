package com.smartICT.PTMS.Services;

import com.smartICT.PTMS.DataAccessLayer.VehicleRepository;
import com.smartICT.PTMS.Entities.Vehicle;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VehicleService {

    private VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle getVehicle(@PathVariable(value ="plate") String plate){
        return vehicleRepository.findById(plate).get();
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public void createVehicle(@RequestBody Vehicle new_vehicle){
        vehicleRepository.save(new_vehicle);
    }

    public ResponseEntity<Vehicle> updateVehicle (@PathVariable(value = "plate") String plate, @RequestBody Vehicle vehicleDetails) throws ResourceNotFoundException {
        Vehicle updatedVehicle = vehicleRepository.findById(plate).orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id : " + plate));

        updatedVehicle.setPlate(vehicleDetails.getPlate());
        updatedVehicle.setBrand(vehicleDetails.getBrand());
        updatedVehicle.setModel(vehicleDetails.getModel());

        return ResponseEntity.ok(updatedVehicle);
    }

    public Map<String ,Boolean> deleteVehicle(@PathVariable(value = "plate") String plate) {
        Vehicle vehicle = vehicleRepository.findById(plate).orElseThrow(() ->new ResolutionException("Person not found for this id  :" + plate ));
        vehicleRepository.delete(vehicle);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
