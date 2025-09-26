package com.example.backend.web;

import com.example.backend.domain.Flight;
import com.example.backend.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    // ✅ Listar todos los vuelos (ADMIN y USER)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Flight> getAll() {
        return service.findAll();
    }

    // ✅ Obtener vuelo por ID (ADMIN y USER)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Flight> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear vuelo (solo ADMIN)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Flight create(@RequestBody Flight flight) {
        return service.save(flight);
    }

    // ✅ Actualizar vuelo (solo ADMIN)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Flight> update(@PathVariable Long id, @RequestBody Flight flight) {
        return service.findById(id)
                .map(existing -> {
                    flight.setId(id);
                    return ResponseEntity.ok(service.save(flight));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar vuelo (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        return service.findById(id)
                .map(existing -> {
                    service.delete(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
