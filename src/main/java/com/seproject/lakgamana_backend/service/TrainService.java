package com.seproject.lakgamana_backend.service;

import com.seproject.lakgamana_backend.entity.Train;
import com.seproject.lakgamana_backend.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {
    
    @Autowired
    private TrainRepository trainRepository;
    
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }
    
    public Optional<Train> getTrainById(Integer id) {
        return trainRepository.findById(id);
    }
    
    public Optional<Train> getTrainByNumber(String trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber);
    }
    
    public List<Train> getTrainSchedule() {
        return trainRepository.findTrainsFromDateTime(LocalDateTime.now());
    }
    
    public List<Train> getTrainScheduleByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return trainRepository.findTrainsBetweenDates(startOfDay, endOfDay);
    }
    
    public Train saveTrain(Train train) {
        return trainRepository.save(train);
    }
    
    public Train updateTrain(Integer id, Train train) {
        return trainRepository.findById(id)
                .map(existingTrain -> {
                    existingTrain.setTrainNumber(train.getTrainNumber());
                    existingTrain.setTrainName(train.getTrainName());
                    existingTrain.setDepartureTime(train.getDepartureTime());
                    existingTrain.setArrivalTime(train.getArrivalTime());
                    return trainRepository.save(existingTrain);
                })
                .orElseThrow(() -> new RuntimeException("Train not found with id: " + id));
    }
    
    public void deleteTrain(Integer id) {
        trainRepository.deleteById(id);
    }
}