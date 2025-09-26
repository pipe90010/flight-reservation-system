package com.example.backend.service;

import com.example.backend.domain.*;
import com.example.backend.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository resRepo;
    private final FlightRepository flightRepo;
    private final PassengerRepository passengerRepo;

    public ReservationService(ReservationRepository resRepo,
                              FlightRepository flightRepo,
                              PassengerRepository passengerRepo) {
        this.resRepo = resRepo;
        this.flightRepo = flightRepo;
        this.passengerRepo = passengerRepo;
    }

    public List<Reservation> findAll() {
        return resRepo.findAll();
    }

    public Reservation findById(Long id) {
        return resRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    }

    @Transactional
    public Reservation create(Long flightId, String name, String email, String phone) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));

        if (flight.getAvailableSeats() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No seats available");
        }

        Passenger passenger = passengerRepo.findByEmailIgnoreCase(email)
                .orElseGet(() -> passengerRepo.save(
                        Passenger.builder()
                                .name(name)
                                .email(email)
                                .phone(phone)
                                .build()
                ));

        // descontar asiento
        flight.setAvailableSeats(flight.getAvailableSeats() - 1);
        flightRepo.save(flight);

        Reservation reservation = Reservation.builder()
                .flight(flight)
                .passenger(passenger)
                .status(ReservationStatus.CONFIRMED)
                .createdAt(Instant.now())
                .build();

        return resRepo.save(reservation);
    }

    public void cancel(Long id) {
        Reservation res = resRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));

        res.setStatus(ReservationStatus.CANCELLED);
        resRepo.save(res);

        // devolver asiento al vuelo
        Flight flight = res.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats() + 1);
        flightRepo.save(flight);
    }
}
