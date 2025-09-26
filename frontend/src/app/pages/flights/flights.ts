import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { FlightsService, Flight } from '../../services/flights.service';

@Component({
  selector: 'app-flights',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './flights.html',
  styleUrls: ['./flights.scss']
})
export class FlightsComponent implements OnInit {
  flights: Flight[] = [];
  newFlight: Flight = {
    origin: '',
    destination: '',
    departureDate: '',
    availableSeats: 0,
    price: 0
  };

  constructor(private flightsService: FlightsService) {}

  ngOnInit(): void {
    this.loadFlights();
  }

  loadFlights(): void {
    this.flightsService.getAll().subscribe({
      next: data => this.flights = data,
      error: err => console.error('Error loading flights', err)
    });
  }

  addFlight(): void {
    this.flightsService.create(this.newFlight).subscribe({
      next: () => {
        this.newFlight = { origin: '', destination: '', departureDate: '', availableSeats: 0, price: 0 };
        this.loadFlights();
      }
    });
  }

  updateFlight(flight: Flight): void {
    if (!flight.id) return;
    this.flightsService.update(flight.id, flight).subscribe({
      next: () => this.loadFlights()
    });
  }

  deleteFlight(id?: number): void {
    if (!id) return;
    this.flightsService.delete(id).subscribe({
      next: () => this.loadFlights()
    });
  }
}
