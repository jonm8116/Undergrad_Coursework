import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicSeries } from '../models/comics/comic-series';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getUserComics(){
    return this.http.get<ComicSeries[]>("/api/series/user", {withCredentials: true});
  }

  //methods to pass data to dashboard-series
  private seriesSource = new BehaviorSubject(null);
  currentSeries = this.seriesSource.asObservable();

  updateCurrentSeries(curSeries: ComicSeries){
    this.seriesSource.next(curSeries);
  }

}
