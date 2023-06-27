import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { FavouriteService } from '../services/favourite.service';
import { UserDataService } from '../services/user-data.service';

@Component({
  selector: 'app-restaurant-search',
  templateUrl: './restaurant-search.component.html',
  styleUrls: ['./restaurant-search.component.css']
})
export class RestaurantSearchComponent {
  searchValue: string = '';

  @Output()
  searchTextChanged: EventEmitter<string> = new EventEmitter<string>();

  searchRestaurant() {
    this.searchTextChanged.emit(this.searchValue);
  }

  clearSearch() {
    this.searchValue = "";
    this.searchTextChanged.emit(this.searchValue);
  }

}
