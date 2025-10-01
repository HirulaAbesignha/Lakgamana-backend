package com.seproject.lakgamana_backend.controller;

import com.seproject.lakgamana_backend.entity.Train;
import com.seproject.lakgamana_backend.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {
    
    @Autowired
    private TrainService trainService;
    
    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        try {
            List<Train> trains = trainService.getAllTrains();
            return ResponseEntity.ok(trains);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Integer id) {
        try {
            return trainService.getTrainById(id)
                    .map(train -> ResponseEntity.ok(train))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/number/{trainNumber}")
    public ResponseEntity<Train> getTrainByNumber(@PathVariable String trainNumber) {
        try {
            return trainService.getTrainByNumber(trainNumber)
                    .map(train -> ResponseEntity.ok(train))
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/schedule")
    public ResponseEntity<List<Train>> getTrainSchedule() {
        try {
            List<Train> trains = trainService.getTrainSchedule();
            return ResponseEntity.ok(trains);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/schedule/{date}")
    public ResponseEntity<List<Train>> getTrainScheduleByDate(@PathVariable String date) {
        try {
            LocalDate journeyDate = LocalDate.parse(date);
            List<Train> trains = trainService.getTrainScheduleByDate(journeyDate);
            return ResponseEntity.ok(trains);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        try {
            Train savedTrain = trainService.saveTrain(train);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTrain);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable Integer id, @RequestBody Train train) {
        try {
            Train updatedTrain = trainService.updateTrain(id, train);
            return ResponseEntity.ok(updatedTrain);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable Integer id) {
        try {
            trainService.deleteTrain(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}