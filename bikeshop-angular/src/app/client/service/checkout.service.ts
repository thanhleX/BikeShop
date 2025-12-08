import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../../enviroment/enviroment';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { PaymentMethod } from '../../dto/PaymentMethod';
import { OrderForm } from '../../dto/OrderForm';
import { Order } from '../../dto/Order';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {
  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getAllPaymentMethod(): Observable<ApiResponse<PaymentMethod[]>> {
    return this.http.get<ApiResponse<PaymentMethod[]>>(`${this.url}/payment-method`)
  }

  finishCheckout(orderForm: OrderForm): Observable<ApiResponse<Order>> {
    return this.http.post<ApiResponse<Order>>(`${this.url}/order`, orderForm)
  }

}
