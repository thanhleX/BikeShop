import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { Blogs } from '../../dto/Blog';
import { enviroment } from '../../enviroment/enviroment';

@Injectable({
  providedIn: 'root'
})
export class BlogService {
  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getBlogById(id:number): Observable<ApiResponse<Blogs>> {
    return this.http.get<ApiResponse<Blogs>>(`${this.url}/blog/${id}`)
  }
}
