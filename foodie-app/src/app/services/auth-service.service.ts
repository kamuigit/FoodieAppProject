import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  constructor() {}
  login: boolean = false;

  isLoggedIn() {
    this.login = true;
  }

  logout() {
    this.login = false;
    localStorage.clear();
  }
}
