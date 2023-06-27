import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AddItemsService {

  url:string = "http://localhost:8081/food/resturant/";

  constructor(private http : HttpClient) { }

  addCity(postdata: any) {
    let httpHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = { headers: httpHeader };
    return this.http.post(this.url + "add/city", postdata, requestOptions);
  }

  ViewProducts(){
    return this.http.get(this.url+"all")
  }

  getDataByCity(city:string){
    return this.http.get(this.url+"allRestaurants/"+city);
  }

  updateProduct(city: string,postdata:any){
    let httpHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = { headers: httpHeader };
    return this.http.put(this.url+"update-city/"+city,postdata,requestOptions);
  }
  deleteProduct(city: string){
    let httpHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = { headers: httpHeader };
    return this.http.delete(this.url+"delete-city/"+city,requestOptions);
  }

  saveRestaurantInCity(city:string,postdata:any){
    let httpHeader = new HttpHeaders({
      'Authorization': 'Bearer ' + localStorage.getItem('jwt')
    });
    let requestOptions = { headers: httpHeader };
    return this.http.put(this.url+"addRestaurant/"+city,postdata,requestOptions);
  }

  saveMenuToRestaurant(city: string, restaurant: string,postdata:any) {
    return this.http.put(this.url + "addMenu/" + city +"/"+ restaurant,postdata);
  }

  deleteRestaurant(city: string,resName:string){
    return this.http.delete(this.url+"delete-restaurant/"+city +"/"+ resName);
  }

  deleteMenu(city: string,resName:string,itemName:string){
    return this.http.delete(this.url+"delete-menu/"+city +"/"+ resName+"/"+itemName);
  }
}
