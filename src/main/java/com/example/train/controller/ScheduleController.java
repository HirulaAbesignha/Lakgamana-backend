package com.example.train.controller;
import com.example.train.dto.CreateScheduleDTO; import com.example.train.entity.Schedule; import com.example.train.service.ScheduleService;
import jakarta.validation.Valid; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/schedules")
public class ScheduleController {
    private final ScheduleService service; public ScheduleController(ScheduleService service){ this.service=service; }
    @GetMapping public List<Schedule> all(){ return service.getAll(); }
    @GetMapping("/{id}") public Schedule get(@PathVariable Long id){ return service.getById(id); }
    @PostMapping public Schedule create(@Valid @RequestBody CreateScheduleDTO dto){ return service.createSchedule(dto);}    
    @PutMapping("/{id}") public Schedule update(@PathVariable Long id, @Valid @RequestBody CreateScheduleDTO dto){ return service.update(id, dto);}    
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ service.delete(id);}    
    @GetMapping("/byRoute/{routeId}") public List<Schedule> byRoute(@PathVariable Long routeId){ return service.findByRoute(routeId);}    
    @GetMapping("/byTrain/{trainId}") public List<Schedule> byTrain(@PathVariable Long trainId){ return service.findByTrain(trainId);}    
}