package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Station;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import com.smartICT.PTMS.Services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {

    private final StationService stationService;

    @Autowired
    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    @GetMapping("/stations/{sid}")
    public Station getStation(@PathVariable(value = "sid") Long sid) {return stationService.getStation(sid);}

    @GetMapping("/stations")
    public List<Station> getAllStations(){
        return stationService.getAllStations();
    }

    @PostMapping("/stations")
    public void creteStation(@RequestBody Station new_station) {
        stationService.createStation(new_station);
    }

    @PutMapping("/stations/{sid}")
    public void updateStation(@PathVariable(value = "sid") Long sid, @RequestBody
    Station stationDetails) throws ResourceNotFoundException {
        stationService.updateStation(sid, stationDetails);
    }

    @DeleteMapping("/stations/{sid}")
    public void deleteStation(@PathVariable(value = "sid") Long sid) throws Exception{
        stationService.deleteStation(sid);
    }
}
