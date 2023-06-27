import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(
    private form: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private auth: AuthServiceService,
    private _snackBar: MatSnackBar
  ) {}
  registrationDetails = this.form.group({
    email: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });
  get email() {
    return this.registrationDetails.get('email');
  }
  get password() {
    return this.registrationDetails.get('password');
  }

  responsedata: any;
  onSubmit() {

    console.log(this.registrationDetails.value);
    this.loginService.login(this.registrationDetails.value).subscribe(
      (respose) => {
        this.auth.isLoggedIn();
        this.responsedata = respose;
        console.log(this.responsedata.token);
        console.log(this.responsedata.message);
        // console.log(respose);

        //Store Token in browser storage
        localStorage.setItem('jwt', this.responsedata.token);
        localStorage.setItem('email', this.responsedata.email);
        localStorage.setItem('role', this.responsedata.role);


this._snackBar.open('Logged In Successfully..','ok',{
  duration:1000
}).afterDismissed().subscribe(() => {
  if (this.responsedata.role =='Admin_Role') {
  this.router.navigate(['/add-items']);
}else{
    this.router.navigate(['/home']);
  }
});

        console.log(this.responsedata.role);
      },
      (error) => {
        alert('invalid user');
      }
    );
  }
}

