import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { User } from '../../dto/User';
import { enviroment } from '../../enviroment/enviroment';
import { Role } from '../../dto/Role';

@Injectable({
  providedIn: 'root'
})
export class UpdateUserService {

  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

 
}
