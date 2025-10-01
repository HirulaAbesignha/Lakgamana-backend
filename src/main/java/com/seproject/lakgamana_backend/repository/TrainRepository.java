package com.seproject.lakgamana_backend.repository;

import com.seproject.lakgamana_backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Optional<Train> findByTrainNumber(String trainNumber);
    
    @Query("SELECT t FROM Train t WHERE t.departureTime >= ?1 ORDER BY t.departureTime")
    List<Train> findTrainsFromDateTime(LocalDateTime fromDateTime);
    
    @Query("SELECT t FROM Train t WHERE t.departureTime BETWEEN ?1 AND ?2 ORDER BY t.departureTime")
    List<Train> findTrainsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}