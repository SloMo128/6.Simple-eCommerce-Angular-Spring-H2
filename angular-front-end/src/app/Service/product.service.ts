import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { Product } from '../Model/product.model';
import { Order } from '../Model/order.model';

@Injectable()
export class ProductService {

    baseURL: string = "http://localhost:8080/api/";

    constructor(private http: HttpClient){}

    getListProduct(): Observable<Product[]> {
        return this.http.get<Product[]>(this.baseURL + 'products/')
    }

    addOrder(status: any): Observable<any> {
        const headers = new HttpHeaders({ 'Content-Type': 'application/json'});
        const body = JSON.stringify(status);
        
        console.log('Sending order:', body);
    
        return this.http.post<any>(this.baseURL + 'orders/create', body, { 'headers': headers })
    }

    addOrderProduct(orderId: number, productId: number, quantity: HttpParams): Observable<any> {
        return this.http.post(`${this.baseURL}orders/${orderId}/products/${productId}`, quantity);
    }

    getAllOrdersProducts(): Observable<any> {
        return this.http.get<any>(this.baseURL + 'orders/order-products' ) ;
           
    }
}    