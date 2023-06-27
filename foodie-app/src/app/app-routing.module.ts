import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RestaurantComponent } from './restaurant/restaurant.component';
import { MenuComponent } from './menu/menu.component';
import { AddItemsComponent } from './add-items/add-items.component';
import { ViewListComponent } from './view-list/view-list.component';
import { UpdateCityComponent } from './update-city/update-city.component';
import { FavouriteComponent } from './favourite/favourite.component';
import { AddRestaurantComponent } from './add-restaurant/add-restaurant.component';
import { AddMenuComponent } from './add-menu/add-menu.component';
import { ActivateGuardGuard } from './guard/activate-guard.guard';
import { CartComponent } from './cart/cart.component';
import { ThankYouPageComponent } from './thank-you-page/thank-you-page.component';
import { MyOrdersComponent } from './my-orders/my-orders.component';
import { ReciptPageComponent } from './recipt-page/recipt-page.component';
import { RefreshComponent } from './refresh/refresh.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'restaurant/:city', component: RestaurantComponent },
  { path: 'menu/:city/:resName', component: MenuComponent },
  { path: 'add-items', component: AddItemsComponent },
  { path: 'profile', component: UserProfileComponent ,
  canActivate: [ActivateGuardGuard],},

  {
    path: 'view-items',
    component: ViewListComponent,
    canActivate: [ActivateGuardGuard],
  },
  { path: 'update/:city', component: UpdateCityComponent ,
  canActivate: [ActivateGuardGuard],},
  {
    path: 'fav',
    component: FavouriteComponent,
    canActivate: [ActivateGuardGuard],
  },
  {
    path: 'add-restaurant/:city',
    component: AddRestaurantComponent,
    canActivate: [ActivateGuardGuard],
  },
  {
    path: 'add-menu/:city/:resName',
    component: AddMenuComponent,
    canActivate: [ActivateGuardGuard],
  },
  {
    path: 'cart',
    component: CartComponent,
     canActivate: [ActivateGuardGuard],
  },
  { path: 'thankYou', component: ThankYouPageComponent,
  canActivate: [ActivateGuardGuard], },
  { path: 'myOrders', component: MyOrdersComponent,
  canActivate: [ActivateGuardGuard],},
  { path: 'recipt/:id', component: ReciptPageComponent ,
  canActivate: [ActivateGuardGuard],},
  { path: 'refresh', component: RefreshComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
