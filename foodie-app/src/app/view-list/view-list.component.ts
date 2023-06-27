import { Component } from '@angular/core';
import { AddItemsService } from '../services/add-items.service';
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-view-list',
  templateUrl: './view-list.component.html',
  styleUrls: ['./view-list.component.css']
})
export class ViewListComponent {

  cityList: any[] | undefined ;
  constructor(private viewService:AddItemsService,private router:Router,private location:Location){}

  ngOnInit() {
    this.viewService.ViewProducts().subscribe((data: any) => {
      this.cityList = data;
    });
  }

  updateProduct(city:string){
    this.router.navigate(['/update',city]);
  }

  back(){
    this.router.navigate(['/add-items'])
  }

  deleteProduct(city:string){
    this.viewService.deleteProduct(city).subscribe(data =>{
      alert("One Item deleted SuccessFully..")
      this.ngOnInit()
    })
  }


  navigateres(city:string){
    this.router.navigate(['/add-restaurant',city]);
  }
  navigatemenu(city:string,resName:string){
    this.router.navigate(['/add-menu',city,resName]);
  }

  deleteRestaurantByName(city: string,resName:string){
    this.viewService.deleteRestaurant(city,resName).subscribe(data =>{
      alert("One Item deleted SuccessFully..")
      this.ngOnInit()
    })
  }

  deletemenuByName(city:string, resName:string,itemName:string){
    console.log("entered");
    this.viewService.deleteMenu(city,resName,itemName).subscribe(data =>{
      alert("One Item deleted SuccessFully..")
      this.ngOnInit()
    })
  }
  goBack(): void {
    this.location.back();
  }
}
