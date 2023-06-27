import { Component, EventEmitter, Output } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { BehaviorSubject, filter, switchMap } from 'rxjs';
import { AuthServiceService } from '../services/auth-service.service';
import { LoginService } from '../services/login.service';
import { UserDataService } from '../services/user-data.service';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  constructor(
    private router: Router,
    private auth: AuthServiceService,
    private loginService: LoginService,
    private userData: UserDataService,
    private cartService: CartService
  ) {}
  userdata: any;
  totalItem: number = 0;
  refreshUser = new BehaviorSubject<boolean>(true);
  ngOnInit() {
    this.router.events
      .pipe(filter((event) => event instanceof NavigationEnd))
      .subscribe(() => {
        this.loadData();
        this.load();
      });
  }

  load() {
    this.cartService.getCart().subscribe(
      (data) => {
        this.totalItem = data.menuList.length;
      },(err)=>{this.totalItem=0;}
    );
  }
  loadData() {
    if (this.auth.login == true) {
      const email = localStorage.getItem('email');
      console.log(email);
      this.loginService.getUserDetails(email).subscribe((response) => {
        this.userdata = response;
        console.log(this.userData);
      });
    } else if (this.auth.login == false) {
      localStorage.clear();
      this.userdata = false;
    }
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/home']);
  }

  goToFav() {
    this.router.navigateByUrl('/fav');
  }

  public get isLoggedIn1() {
    return this.auth.login;
  }

  public get isLoggedIn() {
    if (localStorage.getItem('role') != 'Admin_Role') {
      return this.auth.login;
    }
    return false;
  }

  @Output() SideNavToggle = new EventEmitter();

  openSidenav() {
    this.SideNavToggle.emit();
  }

  navigate() {
    this.router.navigateByUrl('/profile');
  }
}
