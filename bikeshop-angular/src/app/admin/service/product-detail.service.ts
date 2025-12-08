// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { Observable } from 'rxjs';
// import { ApiResponse } from '../../dto/ApiResponse';
// import { Color } from '../../dto/Color';
// import { Handlebar } from '../../dto/Handlebar';
// import { Material } from '../../dto/Material';
// import { enviroment } from '../../enviroment/enviroment';
// import { ProductDto } from '../../dto/FullProduct';
// import { Product } from '../../dto/Product';
// import { ProductDetail } from '../../dto/ProductDetail';

// @Injectable({
//   providedIn: 'root'
// })
// export class ProductDetailService {

//   constructor(private http: HttpClient) { }
//   private url = enviroment.apiUrl;

//   getAllColors(): Observable<ApiResponse<Color[]>> {
//     return this.http.get<ApiResponse<Color[]>>(`${this.url}/color`)
//   }

//   getAllMaterials(): Observable<ApiResponse<Material[]>> {
//     return this.http.get<ApiResponse<Material[]>>(`${this.url}/material`)
//   }

//   getAllHandlebars(): Observable<ApiResponse<Handlebar[]>> {
//     return this.http.get<ApiResponse<Handlebar[]>>(`${this.url}/handlebar`)
//   }

//   getProductDetailById(id: number): Observable<ApiResponse<ProductDetail>> {
//     return this.http.get<ApiResponse<ProductDetail>>(`${this.url}/product-detail/${id}`)
//   }

//   getProductByProductName(name: string): Observable<ApiResponse<Product>> {
//     return this.http.get<ApiResponse<Product>>(`${this.url}/product/name?productName=${name}`)
//   }

//   createNewProductDetail(formData: FormData): Observable<any> {
//     return this.http.post<any>(`${this.url}/product-detail`, formData)
//   }

//   updateProductDetailById(id:number,formData:FormData):Observable<any>{
//     return this.http.put<any>(`${this.url}/product-detail/${id}`,formData)
//   }

//   deleteProductDetailById(id: number): Observable<any> {
//     return this.http.delete<any>(`${this.url}/product-detail/${id}`)
//   }

//   deleteProductImageById(id: number): Observable<any> {
//     return this.http.delete<any>(`${this.url}/image/${id}`)
//   }
// }

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../dto/ApiResponse';
import { Color } from '../../dto/Color';
import { Handlebar } from '../../dto/Handlebar';
import { Material } from '../../dto/Material';
import { enviroment } from '../../enviroment/enviroment';
import { ProductDto } from '../../dto/FullProduct';
import { Product } from '../../dto/Product';
import { ProductDetail } from '../../dto/ProductDetail';
import { PagedResult } from '../../dto/PagedResult';

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService {

  constructor(private http: HttpClient) { }
  private url = enviroment.apiUrl;

  getAllColors(): Observable<ApiResponse<Color[]>> {
    return this.http.get<ApiResponse<Color[]>>(`${this.url}/color`)
  }

  getAllMaterials(): Observable<ApiResponse<Material[]>> {
    return this.http.get<ApiResponse<Material[]>>(`${this.url}/material`)
  }

  getAllHandlebars(): Observable<ApiResponse<Handlebar[]>> {
    return this.http.get<ApiResponse<Handlebar[]>>(`${this.url}/handlebar`)
  }

  getProductDetailById(id: number): Observable<ApiResponse<ProductDetail>> {
    return this.http.get<ApiResponse<ProductDetail>>(`${this.url}/product-detail/${id}`)
  }

  getProductByProductName(name: string): Observable<ApiResponse<Product>> {
    return this.http.get<ApiResponse<Product>>(`${this.url}/product/name?productName=${name}`)
  }

  createNewProductDetail(formData: FormData): Observable<any> {
    return this.http.post<any>(`${this.url}/product-detail`, formData)
  }

  updateProductDetailById(id:number,formData:FormData):Observable<any>{
    return this.http.put<any>(`${this.url}/product-detail/${id}`,formData)
  }

  deleteProductDetailById(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/product-detail/${id}`)
  }

  deleteProductImageById(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/image/${id}`)
  }

  getProductDetailsByProductId(id: number, page: number = 0, size: number = 10): Observable<ApiResponse<PagedResult<ProductDetail[]>>> {
    return this.http.get<ApiResponse<PagedResult<ProductDetail[]>>>(`${this.url}/product-detail?productId=${id}&page=${page}&size=${size}`)
  }
}
