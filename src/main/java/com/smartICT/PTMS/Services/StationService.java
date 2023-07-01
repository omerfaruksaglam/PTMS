package com.smartICT.PTMS.Services;

import com.smartICT.PTMS.DataAccessLayer.StationRepository;
import com.smartICT.PTMS.Entities.Station;
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
public class StationService {

    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public Station getStation(@PathVariable(value ="sid") long sid){
        return stationRepository.findById(sid).get();
    }

    public List<Station> getAllStations(){
        return stationRepository.findAll();
    }

    public void createStation(@RequestBody Station new_station){
        stationRepository.save(new_station);
    }

    public ResponseEntity<Station> updateStation (@PathVariable(value = "sid") long sid, @RequestBody Station stationDetails) throws ResourceNotFoundException {
        Station updatedStation = stationRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Driver not exist with id : " + sid));

        updatedStation.setSid(stationDetails.getSid());
        updatedStation.setStreet(stationDetails.getStreet());
        updatedStation.setSname(stationDetails.getSname());

        return ResponseEntity.ok(updatedStation);
    }

    public Map<String ,Boolean> deleteStation(@PathVariable(value = "sid") long sid) {
        Station station = stationRepository.findById(sid).orElseThrow(() ->new ResolutionException("Person not found for this id  :" + sid ));
        stationRepository.delete(station);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
