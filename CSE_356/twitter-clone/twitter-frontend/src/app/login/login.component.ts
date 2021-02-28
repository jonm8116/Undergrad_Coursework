import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Users } from '../models/users';
import * as jwt_decode from "jwt-decode";

const apiRoute = 'api/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  ngOnInit() {
  }

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  getDecodedAccessToken(token: string): any {
    try{
        return jwt_decode(token);
    }
    catch(Error){
        return null;
    }
  }

  onSubmit(user) {
       this.http.post<Users>(apiRoute, user)
        .subscribe( data => {
            console.log(data);
            var token = document.cookie.split("=")[1];
            console.log(this.getDecodedAccessToken(token));
            this.router.navigate(['/feed']);
        });

  }

}
