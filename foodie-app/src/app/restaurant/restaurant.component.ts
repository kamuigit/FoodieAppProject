import { Component, OnInit } from '@angular/core';
import { UserDataService } from '../services/user-data.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FavouriteService } from '../services/favourite.service';
import { AuthServiceService } from '../services/auth-service.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styleUrls: ['./restaurant.component.css'],
})
export class RestaurantComponent implements OnInit {
  constructor(
    private Data: UserDataService,
    private route: ActivatedRoute,
    private router: Router,
    private fav: FavouriteService,
    private auth: AuthServiceService,
    private location: Location
  ) {}
  ngOnInit() {
    this.route.paramMap.subscribe((p: ParamMap) => {
      this.city = p.get('city');
      this.Data.getAllResturant(this.city).subscribe((resp) => {
        this.restaurantdata = resp;
        console.log(resp);
      });
    });
    // this.data.getAllResturant(this.city).subscribe(
    //   (response) => {
    //     this.restaurantdata = response;
    //   },
    //   (err) => {
    //     alert('unable to get data');
    //   }
    // );
    const favoriteRestaurantsData = localStorage.getItem('favoriteRestaurants');
    if (favoriteRestaurantsData) {
      this.favoriteRestaurants = JSON.parse(favoriteRestaurantsData);
    }
  }
  city: any = 'chennai';
  restaurantdata: any;
  favoriteRestaurants: any[] = [];

  goToMenu(b: any) {
    this.router.navigate(['/menu', this.city, b.resName]);
  }

  addResturantToFav(restaurant: any): void {
    if (this.auth.login == true) {
      this.toggleFavorite(restaurant); // Toggle the favorite status
      this.fav.addResturant(restaurant).subscribe(
        (resp) => {
          console.log(resp);
        },
        (error) => {
          alert('Error while adding the item to favorites');
        }
      );
    } else {
      alert('Please log in to add the item to favorites.');
      this.router.navigate(['/login']);
    }
  }

  toggleFavorite(restaurant: any): void {
    const index = this.favoriteRestaurants.findIndex(
      (res: any) => res.resName === restaurant.resName
    );

    if (index === -1) {
      this.favoriteRestaurants.push(restaurant);
    } else {
      this.favoriteRestaurants.splice(index, 1);
    }

    // Save favorite restaurants to local storage
    localStorage.setItem(
      'favoriteRestaurants',
      JSON.stringify(this.favoriteRestaurants)
    );
  }

  isFavorite(restaurant: any): boolean {
    return this.favoriteRestaurants.some(
      (res: any) => res.resName === restaurant.resName
    );
  }
  restaurant: any;
  n: string = '';
  r: string = ';';

  onSearchTextChanged(restaurant: string) {
    this.n = restaurant.toLowerCase();
    if (restaurant === '' || !restaurant) {
      this.Data.getAllResturant(this.city).subscribe((resp) => {
        this.restaurantdata = resp;
      });
    } else {
      this.restaurantdata = this.restaurantdata.filter((rest: any) => {
        this.r = rest.resName.toLowerCase();
        if (this.r?.startsWith(this.n)) {
          return this.r;
        } else return null;
      });
    }
  }

  goBack(): void {
    this.location.back();
  }

  public get isLoggedIn() {
    if (localStorage.getItem('role') != 'Admin_Role') {
      return true;
    }
    return false;
  }
}
