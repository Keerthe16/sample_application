import { CommonModule  } from '@angular/common';
import { HttpClientModule , HttpClient } from '@angular/common/http';
import { Component , OnInit} from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { response } from 'express';
import { UserService } from '../../user.service';
import { RouterModule } from '@angular/router';


@Component({
  selector: 'app-client-approve',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule,ReactiveFormsModule,RouterModule],
  templateUrl: './client-approve.component.html',
  styleUrl: './client-approve.component.css'
})
export class ClientApproveComponent implements OnInit {
pendingUsers: any[] = [];
message: string = '';

constructor(private userService:UserService) {}
  ngOnInit(): void {
    this.getPendingUsers();
  }


  getPendingUsers(): void {
   this.userService.getUserByStatus().subscribe(
    (data: any[]) => {
      this.pendingUsers = data;
    },
    (error: any) => {
      console.error('Error fetching pending users', error);
    }
   );
  }

  approveUser(id: number) : void {
    
    this.userService.approveUserStatus(id).subscribe(
      (response: any) => {
        console.log(response);
        this.getPendingUsers();
      },
      (error: any) =>
      {
        console.error('Error Updaing user status:' , error);
      }

    );
  }

  rejectUserr(id: number) : void {
    
    this.userService.rejectUserStatus(id).subscribe(
      (response: any) => {
        console.log(response);
        this.getPendingUsers();
      },
      (error: any) =>
      {
        console.error('Error Updaing user status:' , error);
      }

    );
  }

  



}