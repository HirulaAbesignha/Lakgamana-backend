package com.example.train.service;
import com.example.train.dto.CreateScheduleDTO; import com.example.train.entity.Route; import com.example.train.entity.Schedule; import com.example.train.entity.Train; import com.example.train.exception.ResourceNotFoundException;
import com.example.train.repository.RouteRepository; import com.example.train.repository.ScheduleRepository; import com.example.train.repository.TrainRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service @Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepo; private final TrainRepository trainRepo; private final RouteRepository routeRepo;
    public ScheduleService(ScheduleRepository s, TrainRepository t, RouteRepository r){ this.scheduleRepo=s; this.trainRepo=t; this.routeRepo=r; }
    public List<Schedule> getAll(){ return scheduleRepo.findAll(); }
    public Schedule getById(Long id){ return scheduleRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Schedule not found")); }
    public Schedule createSchedule(CreateScheduleDTO dto){ Train train=trainRepo.findById(dto.getTrainId()).orElseThrow(() -> new ResourceNotFoundException("Train not found")); Route route=routeRepo.findById(dto.getRouteId()).orElseThrow(() -> new ResourceNotFoundException("Route not found")); Schedule s=new Schedule(); s.setTrain(train); s.setRoute(route); s.setDepartureTime(dto.getDepartureTime()); s.setArrivalTime(dto.getArrivalTime()); s.setAvailableSeats(dto.getAvailableSeats()!=null?dto.getAvailableSeats():train.getCapacity()); s.setPrice(dto.getPrice()); return scheduleRepo.save(s);}    
    public Schedule update(Long id, CreateScheduleDTO dto){ Schedule s=getById(id); Train train=trainRepo.findById(dto.getTrainId()).orElseThrow(() -> new ResourceNotFoundException("Train not found")); Route route=routeRepo.findById(dto.getRouteId()).orElseThrow(() -> new ResourceNotFoundException("Route not found")); s.setTrain(train); s.setRoute(route); s.setDepartureTime(dto.getDepartureTime()); s.setArrivalTime(dto.getArrivalTime()); s.setAvailableSeats(dto.getAvailableSeats()!=null?dto.getAvailableSeats():train.getCapacity()); s.setPrice(dto.getPrice()); return scheduleRepo.save(s);}    
    public void delete(Long id){ if(!scheduleRepo.existsById(id)) throw new ResourceNotFoundException("Schedule not found"); scheduleRepo.deleteById(id);}    
    public List<Schedule> findByRoute(Long routeId){ return scheduleRepo.findByRouteId(routeId);}    
    public List<Schedule> findByTrain(Long trainId){ return scheduleRepo.findByTrainId(trainId);}    
}