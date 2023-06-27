import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  orderPlaced: any;
  subscribe(arg0: () => void) {
    throw new Error('Method not implemented.');
  }

  constructor(private http: HttpClient) {}

  userOrder: any = {};

  saveToCart(list: any) {
    this.userOrder = {
      email: localStorage.getItem('email'),
      menuList: [list],
    };
    console.log(this.userOrder);
    return this.http.post<any>(
      'http://localhost:8082/api/order/v1/UserOrderAdded',
      this.userOrder
    );
  }

  getCart(): Observable<any> {
    return this.http.get(
      `http://localhost:8082/api/order/v1/getDataByEmail/${localStorage.getItem(
        'email'
      )}`
    );
  }

  deleteItem(email: any, item: any) {
    return this.http.post(
      `http://localhost:8082/api/order/v1/deleteItem/${email}`,
      item
    );
  }

  clearCart(email: any) {
    return this.http.delete(
      `http://localhost:8082/api/order/v1/clearCart/${email}`
    );
  }

  getCurrentOrder(): Observable<any> {
    return this.http.get(
      `http://localhost:8082/api/order/v1/getCurrentOrderByEmail/${localStorage.getItem(
        'email'
      )}`
    );
  }

  viewOrders(): Observable<any> {
    return this.http.get(
      
      `http://localhost:8082/api/order/v1/getAllOrdersByEmail/${localStorage.getItem(
        'email'
      )}`
    );
  }


  confirmOrder(email: any, order: any) {
    return this.http.post<any>(
      'http://localhost:8082/api/order/v1/placeOrderNow/' + email,
      order
    );
  }
}
