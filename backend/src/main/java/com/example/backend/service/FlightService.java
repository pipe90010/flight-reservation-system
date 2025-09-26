package com.example.backend.service;

import com.example.backend.domain.Flight;
import com.example.backend.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository repo;

    public FlightService(FlightRepository repo) {
        this.repo = repo;
    }

    public List<Flight> findAll() {
        return repo.findAll();
    }

    public Optional<Flight> findById(Long id) {
        return repo.findById(id);
    }

    public Flight save(Flight flight) {
        return repo.save(flight);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Flight createFlight(Flight flight) {
        return repo.save(flight);
    }
}
