import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  url:string = "http://localhost:8083/api/favouriteService/v1/";

  constructor(private http:HttpClient) { }
  addResturant(restaurant:any){
   return this.http.post("http://localhost:8081/api/v1/addRestaurant/"+`${localStorage.getItem('email')}`,restaurant)
}

getResturant(email:any){
  return this.http.get(this.url+"resturants/"+`${localStorage.getItem('email')}`)
}
deleteResturant(resName:string){
  return this.http.delete(this.url+"delete/"+`${localStorage.getItem('email')}/`+resName)
}
addMenu(menu:any){
  return this.http.post("http://localhost:8081/api/v1/addMenu/"+`${localStorage.getItem('email')}`,menu )
}

getMenu(){
 return this.http.get(this.url+"get-menu/"+`${localStorage.getItem('email')}`)
}
deleteMenu(resName:string){
 return this.http.delete(this.url+"delete-menu/"+`${localStorage.getItem('email')}/`+resName)
}
}
