import { Component, HostListener } from '@angular/core';
import { CartService } from '../services/cart.service';
import { NewOrder } from 'src/model/order';
import { AuthServiceService } from '../services/auth-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FavouriteService } from '../services/favourite.service';
import { LoginService } from '../services/login.service';

import { FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EmailService } from '../services/email.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent {
  comfirmOrder: boolean = true;
  constructor(
    private form: FormBuilder,
    private cart: CartService,
    private auth: AuthServiceService,
    private router: Router,
    private fav: FavouriteService,
    private _snackBar: MatSnackBar,
    private sendemail: EmailService,
    private location: Location,
    private loginService: LoginService
  ) {}

  cartList: any[] = [];
  amount: number = 0;

  ngOnInit() {
    this.cart.getCart().subscribe({
      next: (data) => {
        this.cartList = data.menuList;
        let itemTotal = 0;
        let totalAmount = 0;
        for (let i = 0; i < this.cartList.length; i++) {
          itemTotal = parseFloat(this.cartList[i].price) * this.cartList[i].qty;
          totalAmount += itemTotal;
        }
        this.amount = totalAmount;
        this.amount = parseFloat(this.amount.toFixed(2));
      },
    });
  }

  flag: boolean = false;

  email = localStorage.getItem('email');
  deleteItem(item: any) {
    item.qty = 1;
    this.cart.deleteItem(this.email, item).subscribe(
      (response) => {
        this.refresh();
        this._snackBar.open('delete successfully', 'done', {
          duration: 2000,
        });
        this.ngOnInit();
      },
      (error) => {
        this._snackBar.open('error while deleting successfully', 'done', {
          duration: 2000,
        });
      }
    );
  }

  orderDetails: any;

  time(): string {
    let time = new Date();
    let currentOffset = time.getTimezoneOffset();
    let ISTOffset = 330;
    let ISTTime = new Date(
      time.getTime() + (ISTOffset + currentOffset) * 60000
    );
    let hours = ISTTime.getHours();
    let minutes = ISTTime.getMinutes();
    let currentTime = '';
    if (hours > 11 && minutes > 0) {
      currentTime = hours + ':' + minutes + ' PM';
    } else {
      currentTime = hours + ':' + minutes + ' AM';
    }
    return currentTime;
  }

  plus(index: number) {
    let inputs = document.getElementsByName('quantity');
    let q = parseInt(this.cartList[index].qty);
    if (q >= 0 && q < 10) {
      q += 1;
      this.cartList[index].qty = q;
      inputs[index].setAttribute('value', this.cartList[index].qty);

      this.amount = this.cartList.reduce(
        (total, item) => total + parseFloat(item.price) * item.qty,
        0
      );
      this.amount = parseFloat(this.amount.toFixed(2));
      console.log(this.amount);
    }
  }

  minus(index: number) {
    let inputs = document.getElementsByName('quantity');
    let q = parseInt(this.cartList[index].qty);
    if (q > 1 && q <= 10) {
      q -= 1;
      this.cartList[index].qty = q;
      inputs[index].setAttribute('value', this.cartList[index].qty);

      this.amount = this.cartList.reduce(
        (total, item) => total + parseFloat(item.price) * item.qty,
        0
      );
      this.amount = parseInt(this.amount.toFixed(2));
    }
  }

  address = this.form.group({
    street: ['', [Validators.required]],
    city: ['', [Validators.required]],
    houseNo: ['', [Validators.required]],
    pinCode: ['', [Validators.required]],
  });

  get street() {
    return this.address.get('street');
  }
  get city() {
    return this.address.get('city');
  }
  get houseNo() {
    return this.address.get('houseNo');
  }
  get pinCode() {
    return this.address.get('pinCode');
  }
  orderPlaced() {
    console.log(this.cartList[0].qty);
    console.log(this.address);
    if (this.auth.login == true) {
      const currentDate = new Date();
      const orders: NewOrder = {
        orderId: Math.floor(Math.random() * 10000),
        dateOfOrder: `${
          currentDate.getMonth() + 1
        }/${currentDate.getDate()}/${currentDate.getFullYear()}`,
        timeOfOrder: `${this.time()}`,
        noOfItems: this.cartList.length,
        amount: this.amount,
        menu: this.cartList,
        address: {
          street: this.address.value.street,
          city: this.address.value.city?.toLowerCase(),
          houseNo: this.address.value.houseNo,
          pinCode: this.address.value.pinCode,
        },
      };
      const customer = {
        email: localStorage.getItem('email'),
        message: `\n Your Order of ₹ ${this.amount}\n with order Id : ${orders.orderId} has been confirmed\nin date : ${orders.dateOfOrder} and time : ${orders.timeOfOrder}\nOrder to address : street = ${orders.address.street},houseNo : ${orders.address.houseNo} ,city : ${orders.address.city}\n,Pincode : ${orders.address.pinCode}
              Thanks for ordering Have a Great Food Journey with our Foodie App!!....`,
      };
      this.sendemail
        .order(customer)
        .subscribe({ next: (Response) => console.log(Response) });

      const email = localStorage.getItem('email');
      this.cart.confirmOrder(email, orders).subscribe(
        (r) => {
          // alert('Order Placed successfully');
          this._snackBar.open('Order Placed successfully', 'done', {
            duration: 2000,
          });

          this.cart
            .clearCart(email)
            .subscribe(() => this.router.navigateByUrl('/thankYou'));
        },
        (e) => {
          alert('unable to place order');
        }
      );
    } else {
      alert('Please log in to place Order.');
      this.router.navigate(['/login']);
    }
  }

  addMenuToFav(menu: any) {
    console.log(menu);
    if (this.auth.login == true) {
      this.fav.addMenu(menu).subscribe(
        (resp) => {
          alert('item added successfully..');
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
  goBack(): void {
    this.location.back();
  }

  checker() {
    const a = this.cartList.filter((b) => {
      if (b.resCity?.toLowerCase() == this.address.value.city?.toLowerCase()) {
        return true;
      } else {
        return false;
      }
    }).length;
    if (a == this.cartList.length) {
      this.pay();
    } else {
      alert(
        'Check delivery address city of order and delivery must be the same ...'
      );
    }
  }

  pay() {
    this.options.amount = (this.amount * 100).toString();
    const rzp1 = new this.loginService.nativeWindow.Razorpay(this.options);
    rzp1.open();
    rzp1.on('payment.failed', function (response: any) {
      console.log(response.error.code);
    });
  }
  @HostListener('window:payment.success', ['$event'])
  onPaymentSuccess(event: any): void {
    this.orderPlaced();
  }
  options = {
    key: 'rzp_test_QhMbVl2PorkzG9',
    amount: '5000',
    currency: 'INR',
    name: 'Foodie',
    description: 'Test Transaction',
    image: 'https://icon-library.com/images/icon-for-food/icon-for-food-14.jpg',
    order_id: '',
    handler: function (response: any) {
      var event = new CustomEvent('payment.success', {
        detail: response,
        bubbles: true,
        cancelable: true,
      });
      window.dispatchEvent(event);
    },
    // "callback_url": "https://eneqd3r9zrjok.x.pipedream.net/",
    prefill: {
      name: 'Gaurav Kumar',
      email: 'gaurav.kumar@example.com',
      contact: '9000090000',
    },
    notes: {
      address: 'Razorpay Corporate Office',
    },
    theme: {
      color: '#3399cc',
    },
  };

  check = true;
  regAdd: any;
  registeredAddress(ch: any) {
    if (ch == false) {
      this.loginService
        .getUserDetails(localStorage.getItem('email'))
        .subscribe((resp) => {
          this.regAdd = resp;
          this.address.setValue({
            street: this.regAdd.street,
            city: this.regAdd.city,
            houseNo: this.regAdd.houseNo,
            pinCode: this.regAdd.pinCode,
          });
        });
    } else {
      this.address.reset({
        street: '',
        city: '',
        houseNo: '',
        pinCode: '',
      });
    }
  }

  refresh(): void {
    this.router
      .navigateByUrl('/refresh', { skipLocationChange: true })
      .then(() => {
        this.router.navigate([decodeURI(this.location.path())]);
      });
  }
}
