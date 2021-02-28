import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Router } from '@angular/router';
import 'rxjs/add/operator/finally';

const apiChapterCreateUrl = "/api/series/chapter/create";
const apiThumbnail = '/api/series/thumbnail/pic';

@Injectable({
  providedIn: 'root'
})
export class DashboardSeriesService {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  currentChapter;

  createComicChapter(comicSeriesId){
    console.log("inside dashboard series service");
    console.log(comicSeriesId);
    this.http.post(apiChapterCreateUrl, comicSeriesId, { withCredentials: true })
      .finally( () => {
        this.router.navigate(['/editor/'+this.currentChapter._id]);
      })
      .subscribe( data => {
        this.comicChapterSource.next(data); //send chapter object to editor
        this.currentChapter = data;
      });
  }

  private comicChapterSource = new BehaviorSubject(null);
  newComicChapter = this.comicChapterSource.asObservable();

  updateThumbnail(thumbnail, comicSeriesId){
    console.log("inside update thumbnail dashboard series service");
    return this.http.post(apiThumbnail+"/"+comicSeriesId.seriesId, thumbnail,  { withCredentials: true });
  }

}
