import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { enviroment } from '../../enviroment/enviroment';
import { PagedResult } from '../../dto/PagedResult';
import { Notification } from '../../dto/Notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getAllNotification(page:number, size:number, userId:number): Observable<ApiResponse<PagedResult<Notification[]>>> {
    return this.http.get<ApiResponse<PagedResult<Notification[]>>>(`${this.url}/notification/${userId}?page=${page}&size=${size}`)
  }

  setNotificationStatusToRead(id:number): Observable<any>{
    return this.http.get<any>(`${this.url}/notification/set-to-read/${id}`)
  }

}
