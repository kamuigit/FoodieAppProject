import { Component } from '@angular/core';
import { UserDataService } from '../services/user-data.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FavouriteComponent } from '../favourite/favourite.component';
import { FavouriteService } from '../services/favourite.service';
import { AuthServiceService } from '../services/auth-service.service';
import { CartService } from '../services/cart.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css'],
})
export class MenuComponent {
  constructor(
    private Data: UserDataService,
    private route: ActivatedRoute,
    private router: Router,
    private fav: FavouriteService,
    private auth: AuthServiceService,
    private cart: CartService,
    private _snackBar: MatSnackBar,
    private location: Location
  ) {}
  ngOnInit() {
    this.route.paramMap.subscribe((p: ParamMap) => {
      this.city = p.get('city');
      this.Data.getAllResturant(this.city).subscribe((resp) => {
        this.restaurantdata = resp;
        this.restaurantdata = this.restaurantdata.filter((a: any) => {
          if (a.resName == this.restaurant) {
            return a.resName;
          }
        });
        this.restaurantdata = this.restaurantdata[0];
        console.log(resp);
      });

      this.restaurant = p.get('resName');
      this.Data.getAllMenu(this.city, this.restaurant).subscribe((resp) => {
        this.menudata = resp;
        console.log(resp);
      });
    });

    const favoriteMenuData = localStorage.getItem('favoriteMenu');
    if (favoriteMenuData) {
      this.favoriteMenu = JSON.parse(favoriteMenuData);
    }
  }

  city: any;
  restaurantdata: any;
  restaurant: any;
  menudata: any;
  favoriteMenu: any[] = [];

  menuData?: any[];
  addMenuToFav(menu: any) {
    console.log(menu);
    if (this.auth.login == true) {
      this.toggleFavorite(menu);
      this.fav.addMenu(menu).subscribe(
        (resp) => {
          console.log(resp);
        },
        (error) => {
          alert('Error While Adding Item To Favourite');
        }
      );
    } else {
      alert('Please log in to add item to Favourite.');
      this.router.navigate(['/login']);
    }
  }

  toggleFavorite(menu: any): void {
    const index = this.favoriteMenu.findIndex(
      (res: any) => res.resName === menu.resName
    );

    if (index === -1) {
      this.favoriteMenu.push(menu);
    } else {
      this.favoriteMenu.splice(index, 1);
    }

    // Save favorite Menu to local storage
    localStorage.setItem('favoriteMenu', JSON.stringify(this.favoriteMenu));
  }

  isFavorite(menu: any): boolean {
    return this.favoriteMenu.some((res: any) => res.itemName === menu.itemName);
  }

  userOrder: any;
  data: any;
  addToCart(menu: any) {
    // console.log(menu);
    if (this.auth.login == true) {
      this.cart.saveToCart(menu).subscribe(
        (response) => {
          // console.log(response);
          this.data = response;
          // console.log(this.data);
          this.refresh();
          this._snackBar.open('Item Added Successfully', 'done', {
            duration: 2000,
          });
        },
        (error) => {
          this._snackBar.open('Item Already Added To Cart', 'done', {
            duration: 1000,
          });
        }
      );
    } else {
      alert('Please log in to add item to cart.');
      this.router.navigate(['/login']);
    }
  }

  n: string = '';
  r: string = ';';

  onSearchTextChanged(menu: string) {
    this.n = menu.toLowerCase();
    if (menu === '' || !menu) {
      this.Data.getAllMenu(this.city, this.restaurant).subscribe((resp) => {
        this.menudata = resp;
      });
    } else {
      this.menudata = this.menudata.filter((menu: any) => {
        this.r = menu.itemName.toLowerCase();
        if (this.r?.startsWith(this.n)) {
          return this.r;
        } else return null;
      });
    }
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

  public get isLoggedIn() {
    if (localStorage.getItem('role') != 'Admin_Role') {
      return true;
    }
    return false;
  }
}
