import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-customerdashboard',
  standalone: true,
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './customerdashboard.component.html',
  styleUrl: './customerdashboard.component.css'
})
export class CustomerdashboardComponent implements OnInit{

  
  products: any[] = []; // To hold active products
 // customerId : number | null = null; // Replace with the logged-in customer's ID
 customerId = 1;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    //this.authService.getLoggedInUser().subscribe({
     // next: (user) => {
        //this.customerId = user.customerId;
        this.loadActiveProducts();
      }
      //error : (err) => {
      //  console.error('Error fetching logged-in user deatils',err);
      //},
    //});
   
  

  loadActiveProducts(): void {
    
    this.productService.getActiveProducts().subscribe((data) => {
      this.products = data;
    });
  }

  

  

}

