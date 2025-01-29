import { Component } from '@angular/core';
import { Validators } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { response } from 'express';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


@Component({
  selector: 'app-register',
  imports: [HttpClientModule, CommonModule, RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm: FormGroup;
  private apiUrl = 'http://localhost:8081/user/register';
  message: { message: string } = { message: '' };

  constructor(private fb: FormBuilder , private http: HttpClient , private router:Router) {
    this.registerForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  

  onSubmit(): void {
    if (this.registerForm.valid) {
      this.http.post<{ message: string }>(this.apiUrl, this.registerForm.value).subscribe({
        next: (response) => {
          this.message = response; 
          this.router.navigate(['/login']); 
        }
      });
    }
  }
}
