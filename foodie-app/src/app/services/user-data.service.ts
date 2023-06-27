import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserDataService {
  constructor(private http: HttpClient) {}

  url = 'http://localhost:8081/food/resturant';
  getAllData() {
    return this.http.get(this.url + '/all');
  }
  //http://localhost:8081/food/resturant/allRestaurants/{city}
  getAllResturant(city: any) {
    return this.http.get(this.url + '/allRestaurants/' + city);
  }
  //http://localhost:8081/food/resturant/menu/
  getAllMenu(city: any, resName: any) {
    return this.http.get(this.url + '/menu/' + city + '/' + resName);
  }
}
