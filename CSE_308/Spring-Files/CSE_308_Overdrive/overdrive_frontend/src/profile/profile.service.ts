import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';
import { Users } from '../models/users/users';
import { ComicSeries } from '../models/comics/comic-series'

const apiUrl = "/api/users/profile";

const followUrl = "/api/series/displayfollows";

const createUrl = "/api/series/user";

const totalLikesUrl = "/api/series/totallikes";

const totalFollowers = "/api/series/totalfollowers";

const totalComics = "/api/series/totalcomics";

const totalFollows = "/api/series/totalfollows";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http:HttpClient) { }

  getUserDetails() {
    return this.http.get<Users>(apiUrl, { withCredentials: true});
  }

  getFollowedSeries(){
    return this.http.get<ComicSeries[]>(followUrl, { withCredentials: true});
  }

  getCreatedSeries(){
    return this.http.get<ComicSeries[]>(createUrl, { withCredentials: true});
  }

  getUserTotalLikes(){
    return this.http.get(totalLikesUrl, { withCredentials: true});
  }

  getUserTotalFollowers(){
    return this.http.get(totalFollowers, { withCredentials: true});
  }

  getUserTotalComics(){
    return this.http.get(totalComics, { withCredentials: true});
  }

  getUserTotalFollows(){
    return this.http.get(totalFollows, { withCredentials: true});
  }
}
