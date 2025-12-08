import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { enviroment } from '../../enviroment/enviroment';
import { User } from '../../dto/User';
import { Role } from '../../dto/Role';

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getUserById(id: number): Observable<ApiResponse<User>> {
    return this.http.get<ApiResponse<User>>(`${this.url}/user/${id}`)
  }

  getAllUser(): Observable<ApiResponse<User[]>> {
    return this.http.get<ApiResponse<User[]>>(`${this.url}/user`)
  }

  deleteUserById(id: number): Observable<ApiResponse<any>> {
    return this.http.delete<ApiResponse<any>>(`${this.url}/user/${id}`)
  }

  getAllRoles(): Observable<ApiResponse<Role[]>> {
    return this.http.get<ApiResponse<Role[]>>(`${this.url}/role`);
  }

  updateUserById(user: any, id: number): Observable<ApiResponse<User>> {
    return this.http.put<ApiResponse<User>>(`${this.url}/user/${id}`, user)
  }

  changePassword(id: number, dto: any): Observable<ApiResponse<User>> {
    return this.http.put<ApiResponse<User>>(`${this.url}/user/${id}/change-password`, dto);
  }

  resetPassword(userId: number) {
  return this.http.put<any>(`${this.url}/user/${userId}/reset-password`, {});
}
}
