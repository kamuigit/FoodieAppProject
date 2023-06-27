import { Component, ViewChild, ElementRef } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { CartService } from '../services/cart.service';
import { Location } from '@angular/common';
import { jsPDF } from 'jspdf';


@Component({
  selector: 'app-recipt-page',
  templateUrl: './recipt-page.component.html',
  styleUrls: ['./recipt-page.component.css']
})
export class ReciptPageComponent {
  constructor(
    private cart: CartService,
    private router: Router,
    private act: ActivatedRoute,
    private location: Location
  ) {}
  ngOnInit() {
    this.act.paramMap.subscribe((p: ParamMap) => {
      this.id = p.get('id');
      this.cart.viewOrders().subscribe(
        (response) => {
          this.orderList = response;
          this.orderList = this.orderList.filter(
            (a: any) => a.orderId == this.id
          );
          this.orderList = this.orderList[0];
        },
        (err) => {
          alert('unable to retrive data');
        }
      );
    });
  }
  id: any;
  orderList: any;

  goBack(): void {
    this.location.back();
  }

  @ViewChild('recipt', { static: false }) el!: ElementRef;

  // width: var(--wid);
  // height: var(--high);
  generatePdf() {
    document.documentElement.style.setProperty('--wid', '595px');
    document.documentElement.style.setProperty('--high', '842px');
    document.documentElement.style.setProperty('--ali', 'left');
    let pdf = new jsPDF('l', 'pt', 'a4');
    pdf.html(this.el.nativeElement, {
      callback: (pdf) => {
        pdf.output('dataurlnewwindow');
      },
    });
    document.documentElement.style.setProperty('--wid', 'auto');
    document.documentElement.style.setProperty('--high', 'auto');
    document.documentElement.style.setProperty('--ali', '');
  }
}
