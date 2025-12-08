import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { Order } from '../../dto/Order';
import { PagedResult } from '../../dto/PagedResult';
import { enviroment } from '../../enviroment/enviroment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;
  
  findOrderById(id:number): Observable<ApiResponse<Order>>{
    return this.http.get<ApiResponse<Order>>(`${this.url}/order/${id}`)
  }
}
