import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicSeries } from '../models/comics/comic-series';
import { forkJoin } from "rxjs/observable/forkJoin";

const apiPopularUrl = '/api/series/popular'

@Injectable({
  providedIn: 'root'
})
export class PopularService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getPopularComics(){
    let action = this.http.get<ComicSeries[]>(apiPopularUrl+"/Action");
    let fantasy = this.http.get<ComicSeries[]>(apiPopularUrl+"/Fantasy");
    let drama = this.http.get<ComicSeries[]>(apiPopularUrl+"/Drama");
    let comedy = this.http.get<ComicSeries[]>(apiPopularUrl+"/Comedy");
    let sports = this.http.get<ComicSeries[]>(apiPopularUrl+"/Sports");
    let horror = this.http.get<ComicSeries[]>(apiPopularUrl+"/Horror");

    return forkJoin([action, fantasy, drama, comedy, sports, horror]);
  }
}
