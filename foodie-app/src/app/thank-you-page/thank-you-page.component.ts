import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-thank-you-page',
  templateUrl: './thank-you-page.component.html',
  styleUrls: ['./thank-you-page.component.css']
})
export class ThankYouPageComponent {
  constructor(private router: Router) {}
  ngOnInit() {
    setTimeout(() => {
      this.router.navigateByUrl('/home');
    }, 5000);
  }
  
}
