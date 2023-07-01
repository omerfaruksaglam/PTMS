package com.smartICT.PTMS.Services;

import com.smartICT.PTMS.DataAccessLayer.DriverRepository;
import com.smartICT.PTMS.DataAccessLayer.RouteRepository;
import com.smartICT.PTMS.Entities.Route;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository,
                        DriverRepository driverRepository) {
        this.routeRepository = routeRepository;
    }

    public Route getRoute(@PathVariable(value = "plate") String plate){return routeRepository.findById(plate).get();}

    public List<Route> getAllRoutes(){return routeRepository.findAll();}

    public void createRoute(@RequestBody Route new_route){
        routeRepository.save(new_route);
    }

    public ResponseEntity<Route> updateRoute (@PathVariable(value = "plate") String plate, @RequestBody Route routeDetails) throws ResourceNotFoundException {
        Route updatedRoute = routeRepository.findById(plate).orElseThrow(() -> new ResourceNotFoundException("Route not exist with plate :" + plate));

        updatedRoute.setPlate(routeDetails.getPlate());
        updatedRoute.setSid(routeDetails.getSid());

        return ResponseEntity.ok(updatedRoute);
    }

    public Map<String, Boolean> deleteRoute(@PathVariable(value = "plate") String plate) throws ResourceNotFoundException {
        Route route = routeRepository.findById(plate).orElseThrow(() -> new ResourceNotFoundException("Route not exist with plate :" + plate));
        routeRepository.delete(route);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
