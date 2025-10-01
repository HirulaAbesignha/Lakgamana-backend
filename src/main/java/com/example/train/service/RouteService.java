package com.example.train.service;
import com.example.train.entity.Route; import com.example.train.repository.RouteRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service @Transactional
public class RouteService {
    private final RouteRepository repo; public RouteService(RouteRepository repo){ this.repo=repo; }
    public List<Route> getAll(){ return repo.findAll(); }
    public Route create(Route r){ return repo.save(r); }
    public Route getById(Long id){ return repo.findById(id).orElseThrow(); }
}