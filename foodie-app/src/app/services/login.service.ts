import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

function _window(): any {
  return window;
}

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  get nativeWindow(): any {
    return _window();
  }

  url: string = 'http://localhost:8085/api/user/v1/login';

  baseUrl: string = 'http://localhost:8081/api/v1/';

  constructor(private http: HttpClient) {}
  register(signup: any) {
    return this.http.post(this.baseUrl + 'register-customer', signup);
  }

  login(logindata: any) {
    return this.http.post(this.url, logindata);
  }

  getUserDetails(email: any) {
    return this.http.get(this.baseUrl + 'user/' + email);
  }
  updateUserDetails(userdata: any) {
    return this.http.put(
      this.baseUrl + 'updateUser/' + `${localStorage.getItem('email')}`,
      userdata
    );
  }
}
