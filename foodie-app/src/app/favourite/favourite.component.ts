import { Component } from '@angular/core';
import { FavouriteService } from '../services/favourite.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent {
  constructor(
    private fav:FavouriteService,
    private router: Router,
    private location:Location
    ){}

  restaurantdata:any;
  menudata:any;
  email=localStorage.getItem('email');

  ngOnInit(): void {
    console.log(this.email);

    this.city=localStorage.getItem("city");
   this.fav.getResturant(this.email).subscribe(response=>{
    console.log(response);
    this.restaurantdata=response;
   })
   this.fav.getMenu().subscribe(response=>{
    console.log(response);
    this.menudata=response;
   })
  }
city:any="chennai";
  goToMenu(b: any) {
    this.router.navigate(['/menu',  b.menu[0].resCity,b.menu[0].resName]);
  }

  deleteres(resName:string){
    this.fav.deleteResturant(resName).subscribe(response=>{
      this.ngOnInit()
      console.log(response);
     })
  }

  deletemenu(resName:string){
    this.fav.deleteMenu(resName).subscribe(response=>{
      this.ngOnInit()
      console.log(response);
     })

  }
  goBack(): void {
    this.location.back();
  }
}
