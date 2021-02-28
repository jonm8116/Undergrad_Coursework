import { Injectable } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import {Users} from "../models/users/users";

const apiUserBio = '/api/users/profile/bio';
const apiUserDisplayName = '/api/users/profile/username';
const apiUserPassword = '/api/users/profile/password';
const apiUserPic = '/api/users/profile/pic';
const apiUrl = "/api/users/profile";

@Injectable({
  providedIn: 'root'
})
export class SettingService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  updateBio(userBio){
    return this.http.post(apiUserBio, userBio, {withCredentials: true});
  }

  updateDisplayName(userDisplayName){
    return this.http.post(apiUserDisplayName, userDisplayName, {withCredentials: true});
  }

  updatePassword(userPassword){
    return this.http.post(apiUserPassword, userPassword, {withCredentials: true});
  }

  updateProfilePic(userPic){
    return this.http.post(apiUserPic, userPic, {withCredentials: true});
  }

  getUserDetails() {
      return this.http.get<Users>(apiUrl, { withCredentials: true});
  }
}
