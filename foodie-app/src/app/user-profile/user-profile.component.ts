import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { LoginService } from '../services/login.service';
import { AuthServiceService } from '../services/auth-service.service';
import { UserDataService } from '../services/user-data.service';
import { Location } from '@angular/common';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})
export class UserProfileComponent {
  constructor(
    private router: Router,
    private auth: AuthServiceService,
    private loginService: LoginService,
    private userData: UserDataService,
    private fb: FormBuilder,
    private location: Location
  ) {}

  profileForm = this.fb.group({
    firstName: [''],
    lastName: [''],
    email: [''],
    password: [''],
    confirmPassword: [''],
    age: [''],
    phoneNo: [''],
    city: [''],
    pinCode: [''],
    street: [''],
    houseNo: [''],
    image: [''],
    role: [''],
  });

  get firstName() {
    return this.profileForm.get('firstName');
  }
  get lastName() {
    return this.profileForm.get('lastName');
  }

  get age() {
    return this.profileForm.get('age');
  }
  get email() {
    return this.profileForm.get('email');
  }
  get confirmPassword() {
    return this.profileForm.get('confirmPassword');
  }
  get password() {
    return this.profileForm.get('password');
  }
  get phoneNo() {
    return this.profileForm.get('phoneNo');
  }
  get city() {
    return this.profileForm.get('city');
  }

  get street() {
    return this.profileForm.get('street');
  }
  get houseNo() {
    return this.profileForm.get('houseNo');
  }
  get pinCode() {
    return this.profileForm.get('pinCode');
  }

  userdata: any;
  ngOnInit() {
    if (this.auth.login == true) {
      const email = localStorage.getItem('email');
      console.log(email);
      this.loginService.getUserDetails(email).subscribe((data: any) => {
        this.userdata = data;
        this.profileForm.setValue({
          firstName: data.firstName,
          lastName: data.lastName,
          email: data.email,
          password: data.password,
          confirmPassword: data.confirmPassword,
          image: data.image,
          age: data.age,
          phoneNo: data.phoneNo,
          city: data.city,
          pinCode: data.pinCode,
          street: data.street,
          houseNo: data.houseNo,
          role: data.role,
        });
      });
    } else if (this.auth.login == false) {
      localStorage.clear();
      this.userdata = false;
    }
  }

  imgUrl: string = '../../assets/user.png';
  uploadfile(event: any) {
    if (event.target.files) {
      let file = new FileReader();
      file.readAsDataURL(event.target.files[0]);
      file.onload = (photo: any) => {
        this.imgUrl = photo.target.result;
        // console.log(photo.target.result);
        this.profileForm.value.image = this.imgUrl;
      };
    }
  }

  editMode = false;
  editProfile() {
    this.editMode = true;
  }

  saveUpdate() {
    this.loginService.updateUserDetails(this.profileForm.value).subscribe(
      (response) => {
        this.editMode = false;
        this.refresh();
      },
      (error) => {
        alert('error');
      }
    );
  }

  goBack(): void {
    this.location.back();
  }
  refresh(): void {
    this.router
      .navigateByUrl('/refresh', { skipLocationChange: true })
      .then(() => {
        this.router.navigate([decodeURI(this.location.path())]);
      });
  }
}
