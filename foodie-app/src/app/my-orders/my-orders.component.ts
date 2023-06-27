import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CartService } from '../services/cart.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.css'],
})
export class MyOrdersComponent {
  constructor(
    private cart: CartService,
    private router: Router,
    private act: ActivatedRoute,
    private location: Location
  ) {}
  ngOnInit() {
    this.cart.viewOrders().subscribe((response) => {
      this.orderList = response;
    });
  }

  goBack(): void {
    this.location.back();
  }
  orderList: any;

  gotoDetails(id: any) {
    this.router.navigate(['/recipt', id]);
  }
}
