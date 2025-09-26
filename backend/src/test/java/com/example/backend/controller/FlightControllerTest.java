package com.example.backend.service;

import com.example.backend.domain.Flight;
import com.example.backend.repository.FlightRepository;
import com.example.backend.service.FlightService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    public FlightServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFlights() {
        Flight f1 = new Flight(1L, "MEX", "NYC", LocalDate.now(), 100, BigDecimal.valueOf(200.0));
        Flight f2 = new Flight(2L, "LAX", "CHI", LocalDate.now(), 80, BigDecimal.valueOf(150.0));

        when(flightRepository.findAll()).thenReturn(Arrays.asList(f1, f2));

        List<Flight> flights = flightService.findAll();

        assertEquals(2, flights.size());
        verify(flightRepository, times(1)).findAll();
    }

    @Test
    void testGetFlightById() {
        Flight flight = new Flight(1L, "MEX", "NYC", LocalDate.now(), 100, BigDecimal.valueOf(200.0));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("NYC", result.get().getDestination());
    }

    @Test
    void testCreateFlight() {
        Flight flight = new Flight(null, "MEX", "LAX", LocalDate.now(), 120, BigDecimal.valueOf(300.0));
        Flight saved = new Flight(1L, "MEX", "LAX", LocalDate.now(), 120, BigDecimal.valueOf(300.0));

        when(flightRepository.save(flight)).thenReturn(saved);

        Flight result = flightService.createFlight(flight);

        assertNotNull(result.getId());
        assertEquals("LAX", result.getDestination());
    }

    @Test
    void testDeleteFlight() {
        doNothing().when(flightRepository).deleteById(1L);

        flightService.delete(1L);

        verify(flightRepository, times(1)).deleteById(1L);
    }
}
