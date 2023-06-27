import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AddItemsService } from '../services/add-items.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent implements OnInit {

  city: string = "";

  constructor(
    private fb: FormBuilder,
    private addItemService: AddItemsService,
    private router: Router,
    private viewService: AddItemsService,
    private route: ActivatedRoute,
    private location:Location,private _snackBar: MatSnackBar
  ) {}

  formdata = this.fb.group({
    // city: ['', Validators.required],
    // restaurantList: this.fb.group({
      resName: ['', Validators.required],
      image: ['', Validators.required],
    // })
  });

  ngOnInit() {
    this.city = this.route.snapshot.params['city'];
    // this.viewService.getDataByCity(city).subscribe((data: any) => {
    //   console.log(data);
    //   this.formdata.patchValue({
    //     city: city,
    //   });
    // });
  }

  onSubmit() {
    this.city = this.route.snapshot.params['city'];
    console.log(this.formdata.value);
    const restauarant={
      resName:this.formdata.value.resName,
      image:this.imgUrl,
      menu:[]
    }

    this.addItemService.saveRestaurantInCity(this.city, restauarant).subscribe(
      response => {
       // alert("Restaurant added successfully");
       this._snackBar.open('Restaurant Added successfully', 'done', {
        duration: 2000,
      }).afterDismissed()
      .subscribe(() => {
        this.router.navigate(['/view-list']);
      });
      },
      error => {
        console.log(error);
        alert("Error occurred while adding the restaurant.");
      }
    );
  }
  goBack(): void {
    this.location.back();
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
  }
}
