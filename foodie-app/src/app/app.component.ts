import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthServiceService } from './services/auth-service.service';
import { CartService } from './services/cart.service';
import { LoginService } from './services/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'foodie-App';

  constructor(
    private router: Router,
    private auth: AuthServiceService,
    private cart: CartService
  ) {}

  logout() {
    this.auth.logout();
    this.router.navigate(['/home']);
  }

  goToFav() {
    this.router.navigateByUrl('/fav');
  }

  public get isLoggedIn() {
    if (
      this.auth.login == true &&
      localStorage.getItem('role') == 'User_Role'
    ) {
      return this.auth.login;
    } else {
      return false;
    }
  }
  public get isLogIn() {
    if (
      this.auth.login == true &&
      localStorage.getItem('role') == 'Admin_Role'
    ) {
      return this.auth.login;
    } else {
      return false;
    }
  }
}
