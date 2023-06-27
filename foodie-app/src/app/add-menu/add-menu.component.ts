import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AddItemsService } from '../services/add-items.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-menu',
  templateUrl: './add-menu.component.html',
  styleUrls: ['./add-menu.component.css']
})
export class AddMenuComponent {
  city: string = "";
  resName:string = "";

  constructor(private fb: FormBuilder,
    private addItemService:AddItemsService,
    private router:Router,
    private route:ActivatedRoute,
    private location:Location,private _snackBar: MatSnackBar) {}

  formdata = this.fb.group({
        menuId: ['', Validators.required],
        itemName: ['', Validators.required],
        category: ['', Validators.required],
        price: ['', Validators.required],
        qty: ['', Validators.required],
        image: ['', Validators.required],
        resName: ['', Validators.required],
        resCity: ['', Validators.required],
  });

  ngOnInit() {
    this.city = this.route.snapshot.params['city'];
    this.resName=this.route.snapshot.params['resName'];
    this.formdata.setValue({
      menuId: "",
        itemName: "",
        category: "",
        price: "",
        qty: "1",
        image: "",
        resName: this.resName,
        resCity: this.city,
    })
  }


  onSubmit(){
    this.formdata.value.image = this.imgUrl;
    this.addItemService.saveMenuToRestaurant(this.city,this.resName,this.formdata.value).subscribe(
      response => {
        //alert("Menu added successfully");
        this._snackBar.open('Menu Added successfully', 'done', {
          duration: 2000,
        }).afterDismissed()
        .subscribe(() => {
          this.router.navigate(['/view-list']);
        });
      },
      error => {
        console.log(error);
        alert("Error occurred while adding the Menu.");
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
    // console.log(this.user);
  }
}
