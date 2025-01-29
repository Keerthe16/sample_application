import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-login',
  imports: [RouterModule, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  
  loginForm: FormGroup;
  message: string = '';
  private apiUrl = 'http://localhost:8081/user/login';
  
  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    }); 
  }
  
  onSubmit(): void {
    if (this.loginForm.valid) {
      this.http.post(this.apiUrl, this.loginForm.value, { responseType: 'text' }).pipe(
        catchError(err => {
          this.message = err.error;
          return of(null);
        })
      ).subscribe(role => {
        if (role) {
          if (role === 'ADMIN') {
            this.router.navigate(["/admin"]);
          } else if (role === 'CUSTOMER') {
            this.router.navigate(["/customer"]);
          }
        }
      });
    }
  }
}
