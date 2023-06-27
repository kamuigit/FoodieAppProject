import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { UserDataService } from '../services/user-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthServiceService } from '../services/auth-service.service';
import { LoginService } from '../services/login.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private data: UserDataService,
    private route: ActivatedRoute,
    private router: Router,
    private auth: AuthServiceService,
    private loginService: LoginService
  ) {}

  images: string[] = [
    'https://media.istockphoto.com/id/545286388/photo/chinese-food-blank-background.jpg?b=1&s=170667a&w=0&k=20&c=ipMtvr-QmWrqBWleY3aVy7-uiyw9NYqTT6nm8vfuVRc=',
    'https://cdn.pixabay.com/photo/2016/12/26/17/28/spaghetti-1932466_1280.jpg',
    'https://c4.wallpaperflare.com/wallpaper/234/543/684/food-pizza-wallpaper-preview.jpg',
    '/assets/bg.jpg',
    'https://media.istockphoto.com/id/545286388/photo/chinese-food-blank-background.jpg?b=1&s=170667a&w=0&k=20&c=ipMtvr-QmWrqBWleY3aVy7-uiyw9NYqTT6nm8vfuVRc=',
    'https://cdn.pixabay.com/photo/2016/12/26/17/28/spaghetti-1932466_1280.jpg',
    'https://c4.wallpaperflare.com/wallpaper/234/543/684/food-pizza-wallpaper-preview.jpg',
    '/assets/bg.jpg',
    'https://media.istockphoto.com/id/545286388/photo/chinese-food-blank-background.jpg?b=1&s=170667a&w=0&k=20&c=ipMtvr-QmWrqBWleY3aVy7-uiyw9NYqTT6nm8vfuVRc=',
    'https://cdn.pixabay.com/photo/2016/12/26/17/28/spaghetti-1932466_1280.jpg',
    'https://c4.wallpaperflare.com/wallpaper/234/543/684/food-pizza-wallpaper-preview.jpg',
    '/assets/bg.jpg',
  ];
  currentIndex = 0;

  @ViewChild('homebox', { static: false }) el!: ElementRef;

  ngOnInit(): void {
    // document.documentElement.style.setProperty('--var', 'url()');
    this.changeImg();
    this.data.getAllData().subscribe(
      (response) => {
        this.citydata = response;
      },
      (err) => {
        alert('unable to get data');
      }
    );
  }
  changeImg() {
    this.images.forEach((img, i) => {
      setTimeout(() => {
        document.documentElement.style.setProperty('--var', `url('${img}')`);
      }, i * 6000);
    });

    // setInterval(() => {
    //   this.currentIndex = (this.currentIndex + 1) % this.images.length;
    //   document.documentElement.style.setProperty(
    //     '--var',
    //     `url(${this.images[this.currentIndex]})`
    //   );
    // }, 5000);
  }
  citydata: any;
  goToRestaurant(a: any) {
    this.router.navigate(['/restaurant', a.city]);
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/']);
  }
  public get isLoggedIn() {
    return this.auth.login;
  }
}
