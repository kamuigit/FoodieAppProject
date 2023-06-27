import { Component } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
})
export class FooterComponent {
  goToMap() {
    window.open('https://goo.gl/maps/iPFVdrdsm2con2XL9');
  }
}
