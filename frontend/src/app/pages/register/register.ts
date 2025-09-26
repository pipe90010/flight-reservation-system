import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.html',
  styleUrls: ['./register.scss']
})
export class RegisterComponent {
  username = '';
  password = '';
  error = '';

  constructor(private auth: AuthService) {}

  onSubmit(): void {
    this.auth.register(this.username, this.password).subscribe({
      error: () => (this.error = 'Registration failed')
    });
  }
}
