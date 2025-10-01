package com.example.train.controller;
import com.example.train.dto.CreateRouteDTO; import com.example.train.entity.Route; import com.example.train.service.RouteService;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/routes")
public class RouteController {
    private final RouteService service; public RouteController(RouteService service){ this.service=service; }
    @GetMapping public List<Route> all(){ return service.getAll(); }
    @PostMapping public Route create(@Valid @RequestBody CreateRouteDTO dto){ Route r=new Route(); r.setStartStation(dto.getStartStation()); r.setEndStation(dto.getEndStation()); r.setDistanceKm(dto.getDistanceKm()); return service.create(r);}    
}