import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSelectModule } from '@angular/material/select';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './services/login.service';
import { HttpClientModule } from '@angular/common/http';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { MenuComponent } from './menu/menu.component';
import { UserDataService } from './services/user-data.service';
import { AddItemsComponent } from './add-items/add-items.component';
import { ViewListComponent } from './view-list/view-list.component';
import { UpdateCityComponent } from './update-city/update-city.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { CartComponent } from './cart/cart.component';
import { AddItemsService } from './services/add-items.service';
import { FavouriteService } from './services/favourite.service';
import { CartService } from './services/cart.service';
import { AuthServiceService } from './services/auth-service.service';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { RestaurantSearchComponent } from './restaurant-search/restaurant-search.component';
import { ThankYouPageComponent } from './thank-you-page/thank-you-page.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { ReciptPageComponent } from './recipt-page/recipt-page.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { RefreshComponent } from './refresh/refresh.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    RestaurantComponent,
    MenuComponent,
    AddItemsComponent,
    ViewListComponent,
    UpdateCityComponent,
    FavouriteComponent,
    AddRestaurantComponent,
    AddMenuComponent,
    CartComponent,
    RestaurantSearchComponent,
    ThankYouPageComponent,
    MyOrdersComponent,
    ReciptPageComponent,
    FooterComponent,
    HeaderComponent,
    RefreshComponent,
    UserProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatToolbarModule,
    MatButtonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatMenuModule,
  ],
  providers: [
    LoginService,
    UserDataService,
    AddItemsService,
    FavouriteService,
    CartService,
    AuthServiceService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
