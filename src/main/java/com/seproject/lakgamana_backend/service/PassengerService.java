package com.seproject.lakgamana_backend.service;

import com.seproject.lakgamana_backend.entity.Passenger;
import com.seproject.lakgamana_backend.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    
    @Autowired
    private PassengerRepository passengerRepository;
    
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }
    
    public Optional<Passenger> getPassengerById(Integer id) {
        return passengerRepository.findById(id);
    }
    
    public Optional<Passenger> getPassengerByEmail(String email) {
        return passengerRepository.findByEmail(email);
    }
    
    public Passenger savePassenger(Passenger passenger) {
        if (passenger.getEmail() != null && passengerRepository.existsByEmail(passenger.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return passengerRepository.save(passenger);
    }
    
    public Passenger updatePassenger(Integer id, Passenger passenger) {
        return passengerRepository.findById(id)
                .map(existingPassenger -> {
                    existingPassenger.setName(passenger.getName());
                    existingPassenger.setGender(passenger.getGender());
                    existingPassenger.setAge(passenger.getAge());
                    existingPassenger.setContactNum(passenger.getContactNum());
                    if (passenger.getEmail() != null && !passenger.getEmail().equals(existingPassenger.getEmail())) {
                        if (passengerRepository.existsByEmail(passenger.getEmail())) {
                            throw new RuntimeException("Email already exists");
                        }
                        existingPassenger.setEmail(passenger.getEmail());
                    }
                    return passengerRepository.save(existingPassenger);
                })
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
    }
    
    public void deletePassenger(Integer id) {
        passengerRepository.deleteById(id);
    }
}