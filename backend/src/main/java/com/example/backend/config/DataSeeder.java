package com.example.backend.config;

import com.example.backend.domain.*;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedData(UserRepository userRepo,
                               FlightRepository flightRepo,
                               PasswordEncoder encoder) {
        return args -> {
            // Usuarios
            if (userRepo.findByUsername("admin").isEmpty()) {
                userRepo.save(new User(null, "admin", encoder.encode("admin123"), Role.ADMIN));
            }
            if (userRepo.findByUsername("user").isEmpty()) {
                userRepo.save(new User(null, "user", encoder.encode("user123"), Role.USER));
            }
            // Juan NO es admin por defecto
            if (userRepo.findByUsername("juan").isEmpty()) {
                userRepo.save(new User(null, "juan", encoder.encode("1234"), Role.USER));
            }

            // Vuelos
            if (flightRepo.count() == 0) {
                flightRepo.save(Flight.builder()
                        .origin("NYC")
                        .destination("LON")
                        .departureDate(LocalDate.now().plusDays(1))
                        .availableSeats(120)
                        .price(new BigDecimal("450.00"))
                        .build());

                flightRepo.save(Flight.builder()
                        .origin("BER")
                        .destination("PAR")
                        .departureDate(LocalDate.now().plusDays(2))
                        .availableSeats(80)
                        .price(new BigDecimal("120.00"))
                        .build());
            }
        };
    }
}
