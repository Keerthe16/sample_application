import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  

  constructor(private http:HttpClient) { }

  getUserByStatus(): Observable<any[]> {
    const baseUrl = 'http://localhost:8081/user/admin/pendingUsers';
    return this.http.get<any[]>(baseUrl);

  }

  approveUserStatus(id: number): Observable<any> {
    const url = `http://localhost:8081/user/admin/approve/${id}`;
    return this.http.put<number>(url,{});
  }

  rejectUserStatus(id: number): Observable<any> {
    const url = `http://localhost:8081/user/admin/reject/${id}`;
    return this.http.put<number>(url,{});
  }
}