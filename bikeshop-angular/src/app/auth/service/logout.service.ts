import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../../enviroment/enviroment';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  logout(token: string) {
    return this.http.post(`${this.url}/auth/logout`, {token}, {
      headers:new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }
}
