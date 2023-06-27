import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from '../services/email.service';
import { CustomValidator } from 'src/model/customvalidators';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  constructor(
    private form: FormBuilder,
    private loginService: LoginService,
    private _snackBar: MatSnackBar,
    private sendemail: EmailService,
    private router: Router
  ) {}
  registrationDetails = this.form.group({
    firstName: ['', [Validators.required]],
    lastName: ['', [Validators.required,]],
    email: ['', Validators.required],
    age: ['', [CustomValidator.AgeVerify, Validators.required, Validators.pattern(/\d+/)]],
    password: [
      '',
      [
        Validators.pattern(
          /(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])(?=\D*\d)(?=.*?[\!\#\@\$\%\&\/\(\)\=\?\*\-\+\-\_\.\:\;\,\]\[\{\}\^])[A-Za-z0-9\!\#\@\$\%\&\/\(\)\=\?\*\-\+\-\_\.\:\;\,\]\[\{\}\^]{8,}$/
        ),
        Validators.required,
      ],
    ],
    confirmPassword: ['', Validators.required],
    phoneNo: ['', [Validators.required, Validators.pattern(/^[789]\d{9,9}$/)]],
    city: ['', Validators.required],
    houseNo: ['', Validators.required],
    street: ['', Validators.required],
    pinCode: ['', [Validators.required, Validators.pattern('^[0-9]{4,6}$')]],
    image: [''],
  }, { validators: [CustomValidator.checkPasswords] });

  get firstName() {
    return this.registrationDetails.get('firstName');
  }
  get lastName() {
    return this.registrationDetails.get('lastName');
  }

  get age() {
    return this.registrationDetails.get('age');
  }
  get email() {
    return this.registrationDetails.get('email');
  }
  get confirmPassword() {
    return this.registrationDetails.get('confirmPassword');
  }
  get password() {
    return this.registrationDetails.get('password');
  }
  get phoneNo() {
    return this.registrationDetails.get('phoneNo');
  }
  get city() {
    return this.registrationDetails.get('city');
  }

  get street() {
    return this.registrationDetails.get('street');
  }
  get houseNo() {
    return this.registrationDetails.get('houseNo');
  }
  get pinCode() {
    return this.registrationDetails.get('pinCode');
  }

  isSubmitted = false;
  isRegistered = false;

  // user: any = {
  //   firstName: '',
  //   lastName: '',
  //   email: '',
  //   password: '',
  //   confirmPassword: '',
  //   age: '',
  //   phoneNo: '',
  //   image: '',
  //   role: '',
  //   city: '',
  //   street: '',
  //   pinCode: '',
  //   houseNo: '',
  // };

  imgUrl: string = '../../assets/user.png';
  uploadfile(event: any) {
    if (event.target.files) {
      let file = new FileReader();
      file.readAsDataURL(event.target.files[0]);
      file.onload = (photo: any) => {
        this.imgUrl = photo.target.result;
        console.log(this.imgUrl);
        
        // this.user.image = this.registrationDetails.value.image;
      };
    }
    // console.log(this.user);
  }

  onSubmit() {
    const customer = {
      email: this.registrationDetails.value.email,
      message: '',
    };
    this.registrationDetails.value.image = this.imgUrl;
    console.log(this.registrationDetails.value);
    this.loginService.register(this.registrationDetails.value).subscribe(
      (respose) => {
        this.sendemail.register(customer).subscribe((respose) => {
          console.log(respose);
        });
        this._snackBar
          .open('Registered Successfully..', 'ok', {
            duration: 2000,
          })
          .afterDismissed()
          .subscribe(() => {
            this.router.navigate(['/login']);
          });
      },
      (error) => {
        alert('invalid user');
      }
    );
  }
}
