import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { Blogs } from '../../dto/Blog';
import { PagedResult } from '../../dto/PagedResult';
import { enviroment } from '../../enviroment/enviroment';
import { ProductSubCategories } from '../../dto/ProductSubCategories';
import { ProductCategories } from '../../dto/ProductCategories';

@Injectable({
  providedIn: 'root'
})
export class ProductSubCategoriesService {

  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getAllProductSubCategories(productSubCategoryId: number): Observable<ApiResponse<PagedResult<ProductSubCategories[]>>> {
    return this.http.get<ApiResponse<PagedResult<ProductSubCategories[]>>>(`${this.url}/product-sub-categories?productCategoryId=${productSubCategoryId}`);
  }

  getAllProductCategories(): Observable<ApiResponse<PagedResult<ProductCategories[]>>> {
    return this.http.get<ApiResponse<PagedResult<ProductCategories[]>>>(`${this.url}/product-categories`);
  }

  getProductSubCategoryByName(
    categoryName: string,
    page: number,
    size: number
  ): Observable<ApiResponse<PagedResult<ProductSubCategories[]>>> {
    return this.http.get<ApiResponse<PagedResult<ProductSubCategories[]>>>(
      `${this.url}/product-sub-categories/name?categoryName=${categoryName}&page=${page}&size=${size}`
    );
  }
}
