import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';

interface TokenResponse {
  token: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private api = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient, private router: Router) {}

  login(username: string, password: string): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.api}/login`, { username, password }).pipe(
      tap(res => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/flights']);
      })
    );
  }

  register(username: string, password: string): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(`${this.api}/register`, { username, password }).pipe(
      tap(res => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/flights']);
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }
}
