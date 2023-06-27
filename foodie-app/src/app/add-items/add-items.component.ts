import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AddItemsService } from '../services/add-items.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-items',
  templateUrl: './add-items.component.html',
  styleUrls: ['./add-items.component.css']
})
export class AddItemsComponent {

  constructor(private fb: FormBuilder,private addItemService:AddItemsService,private _snackBar: MatSnackBar,private router:Router) {}

  formdata = this.fb.group({
    city: ['', Validators.required],
    image: ['', Validators.required],
  });

  data:any;
  onSubmit() {
    console.log(this.formdata.value);
    const city={
      city:this.formdata.value.city,
      image:this.imgUrl,
      restaurantList:[]
    }
    this.addItemService.addCity(city).subscribe(
      response => {
        this.data = response;
        console.log(this.data);
        //alert("Success...:)");
        this._snackBar.open('City Added successfully', 'done', {
          duration: 2000,
        });
      },
      error => {
        alert("Fail To Find Data..Try Again :(");
      }
    );
  }

  back(){
    this.router.navigate(['/add-items'])
  }
  next(){
    this.router.navigate(['/view-items'])
  }

  navigateres(){
    this.router.navigate(['/add-restaurant']);
  }
  navigatemenu(){
    this.router.navigate(['/add-menu']);
  }

  imgUrl: string = '../../assets/user.png';
  uploadfile(event: any) {
    if (event.target.files) {
      let file = new FileReader();
      file.readAsDataURL(event.target.files[0]);
      file.onload = (photo: any) => {
        this.imgUrl = photo.target.result;
        console.log(this.imgUrl);
        
      };
    }
    // console.log(this.user);
  }
}
