import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})

export class EmailService {


  constructor(private http:HttpClient) { }

  register(user:any){
    const customer={
      email:user.email,
      message:user.message
    }
    console.log(customer);
    return this.http.post("http://localhost:9190/mail/sendmail",customer);

  }
  order(user:any){
    const customer={
      email:user.email,
      message:user.message
    }
    console.log(customer);
    return this.http.post("http://localhost:9190/mail/sendordermail",customer);

  }
}
