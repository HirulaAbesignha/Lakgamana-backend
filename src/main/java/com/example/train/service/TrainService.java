package com.example.train.service;
import com.example.train.entity.Train; import com.example.train.repository.TrainRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @Transactional
public class TrainService {
    private final TrainRepository repo; public TrainService(TrainRepository repo){ this.repo=repo; }
    public List<Train> getAll(){ return repo.findAll(); }
    public Train create(Train t){ return repo.save(t); }
    public Train getById(Long id){ return repo.findById(id).orElseThrow(); }
}