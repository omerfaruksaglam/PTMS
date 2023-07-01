package com.smartICT.PTMS.Controller;

import com.smartICT.PTMS.Entities.Route;
import com.smartICT.PTMS.Excepitions.ResourceNotFoundException;
import com.smartICT.PTMS.Services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes/{plate}")
    public Route getRoute(@PathVariable(value = "plate") String plate) {return routeService.getRoute(plate);}

    @GetMapping("/routes")
    public List<Route> getAllRoutes(){
        return routeService.getAllRoutes();
    }

    @PostMapping("/routes")
    public void creteRoute(@RequestBody Route new_Route) {
        routeService.createRoute(new_Route);
    }

    @PutMapping("/routes/{plate}")
    public void updateRoute(@PathVariable(value = "plate") String plate, @RequestBody
    Route routeDetails) throws ResourceNotFoundException {
        routeService.updateRoute(plate, routeDetails);
    }

    @DeleteMapping("/routes/{plate}")
    public void deleteRoute(@PathVariable(value = "plate") String plate) throws Exception{
        routeService.deleteRoute(plate);
    }
 }
