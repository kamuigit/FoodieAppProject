import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AddItemsService } from '../services/add-items.service';

@Component({
  selector: 'app-update-city',
  templateUrl: './update-city.component.html',
  styleUrls: ['./update-city.component.css']
})
export class UpdateCityComponent {
  constructor(private router:Router,private fb:FormBuilder,private route: ActivatedRoute,private viewService:AddItemsService){}

  formdata = this.fb.group({
    city: ['', Validators.required],
    restaurantList: this.fb.group({
      resImage: [''],
      menu: this.fb.group({
        menuId: ['', Validators.required],
        itemName: ['', Validators.required],
        category: ['', Validators.required],
        price: ['', Validators.required],
        qty: ['', Validators.required],
        menuImage: [''],
        menuResName: ['', Validators.required],
        menuresCity: ['', Validators.required],
      }),
      resName: ['', Validators.required],
    })
  });

  ngOnInit() {
    const city = this.route.snapshot.params['city'];
  this.viewService.getDataByCity(city).subscribe(
    (data: any) => {
      console.log(data);
      
      this.formdata.setValue({
        city:city,
        restaurantList: {
          resImage: data.resImage,
          menu: {
            category: data.category,
            menuImage: data.menuImage,
            itemName: data.itemName,
            menuId: data.menuId,
            price: data.price,
            qty: data.qty,
            menuresCity: data.menuresCity,
            menuResName: data.menuResName
          },
          resName: data.resName,
        } 
      });
    },
    error => {
      console.log(error);
    }
  );
  }

  data:any;

  onSubmit(){
    const city = this.route.snapshot.params['city'];
    this.viewService.updateProduct(city,this.formdata.value).subscribe(
      response=>{
        this.data = response;
        console.log(this.data);
        
        this.router.navigate(["/view-items"])
        console.log(this.data);
        
        alert("Success...:)");
      },
      error => {
        alert("Fail To Find Data..Try Again :(");
      }
    )
  }

  back(){
    this.router.navigate(['/view-items'])
  }
}
