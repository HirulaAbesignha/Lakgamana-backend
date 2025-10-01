package com.example.train.controller;
import com.example.train.entity.Train; import com.example.train.service.TrainService;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/trains")
public class TrainController {
    private final TrainService service; public TrainController(TrainService service){ this.service=service; }
    @GetMapping public List<Train> all(){ return service.getAll(); }
    @PostMapping public Train create(@RequestBody Train t){ return service.create(t); }
}