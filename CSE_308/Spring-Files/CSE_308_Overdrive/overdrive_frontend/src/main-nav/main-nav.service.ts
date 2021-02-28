import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const apiUrl = "/api/login/logout";

@Injectable({
  providedIn: 'root'
})
export class MainNavService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  logoutUser(){
    return this.http.get(apiUrl);
  }
}
