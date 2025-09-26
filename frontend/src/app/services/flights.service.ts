import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Flight {
  id?: number;
  origin: string;
  destination: string;
  departureDate: string;
  availableSeats: number;
  price: number;
}

@Injectable({ providedIn: 'root' })
export class FlightsService {
  private api = 'http://localhost:8080/api/flights';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Flight[]> {
    return this.http.get<Flight[]>(this.api);
  }

  create(flight: Flight): Observable<Flight> {
    return this.http.post<Flight>(this.api, flight);
  }

  update(id: number, flight: Flight): Observable<Flight> {
    return this.http.put<Flight>(`${this.api}/${id}`, flight);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
