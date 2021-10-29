import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class ProductService {


  //private baseUrl = 'http://localhost:8066/api/productCategoryId/1';
  //private baseUrl = 'http://localhost:8066/api/products';
  private baseUrl = 'http://localhost:8066/api/products';

  private categoryUrl = 'http://localhost:8066/api/product-category';

  constructor(private httpClient: HttpClient) { }

  getProductListPaginate(thePage: number, thePageSize: number, theCategoryId: number): Observable<any>{

    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}` + `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<any>(searchUrl);
  }


 getProductList(theCategoryId: number): Observable<any>{

    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;

    return this.httpClient.get<any>(searchUrl);
  } 


  getProductCategories(): Observable<any>{

    return this.httpClient.get<any>(this.categoryUrl);
  }


  searchProducts(theKeyword: string): Observable<any>{
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`;

    return this.httpClient.get<any>(searchUrl);
  }

  searchProductsPaginate(thePage: number, thePageSize: number, theKeyword: string): Observable<any>{
    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}` + `&page=${thePage}&size=${thePageSize}`;

    return this.httpClient.get<any>(searchUrl);
  }


  getProduct(theProductId: number): Observable<Product>{

    const productUrl = `${this.baseUrl}/${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
  }



}




