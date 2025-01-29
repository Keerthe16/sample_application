import { Component , OnInit} from '@angular/core';
import { ProductService } from '../../product.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-product-page',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule,CommonModule,HttpClientModule],
  templateUrl: './product-page.component.html',
  styleUrl: './product-page.component.css'
})
export class ProductPageComponent implements OnInit {
  products: any[] = [];
  newProduct: any = { };
  editProduct: any = null;

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getAllProducts().subscribe(
      (data: any[]) => {
        this.products = data;
      },
      (error) => {
        console.error('Error loading products', error);
      }
    );
  }

  createProduct() {
    this.productService.createProduct(this.newProduct).subscribe(
      (data: any) => {
        this.products.push(data); // Add new product to the list
        this.newProduct = {  };
      },
      (error) => {
        console.error('Error creating product', error);
      }
    );
  }

  updateProduct() {
    if (this.editProduct) {
      this.productService.updateProduct(this.editProduct.id, this.editProduct).subscribe(
        (data: any) => {
          const index = this.products.findIndex(p => p.id === data.id);
          if (index !== -1) {
            this.products[index] = data;
          }
          this.editProduct = null;
        },
        (error) => {
          console.error('Error updating product', error);
        }
      );
    }
  }

  deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe(
      () => {
        this.products = this.products.filter(product => product.id !== id);
      },
      (error) => {
        console.error('Error deleting product', error);
      }
    );
  }

  setEditProduct(product: any) {
    this.editProduct = { ...product };
  }
}