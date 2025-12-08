import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { enviroment } from '../../enviroment/enviroment';
import { Role } from '../../dto/Role';
import { ApiResponse } from '../../dto/ApiResponse';


@Injectable({
  providedIn: 'root'
})
export class SignUpService {
  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  signUp(signUp: any): Observable<any> {
    const token = localStorage.getItem('token');
    const headers = {
      Authorization: `Bearer ${token}`
    };
    return this.http.post<any>(`${this.url}/user`, signUp, { headers });
  }

  getAllRoles(): Observable<ApiResponse<Role[]>> {
    return this.http.get<ApiResponse<Role[]>>(`${this.url}/role`);
  }
}
