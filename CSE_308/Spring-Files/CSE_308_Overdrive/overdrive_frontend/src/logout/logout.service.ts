import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const apiUrl = "/api/login/logout";

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  callLogoutUser(){
    return this.http.get(apiUrl);
  }
}
