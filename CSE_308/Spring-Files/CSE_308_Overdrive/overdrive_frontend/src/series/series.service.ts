import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicChapter } from '../models/comics/comic-chapter';

const apiChapterUrl = "/api/series/chapter";
const apiRatingUrl = "/api/series/rating";
const apiLikeUrl = "/api/series/chapter/like";

@Injectable({
  providedIn: 'root'
})
export class SeriesService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getSeriesChapters(seriesId){
    console.log("seriesId");
    console.log(seriesId);
    return this.http.get<ComicChapter[]>(apiChapterUrl+"/"+seriesId);
  }

  setSeriseRating(seriesRating) {
      console.log("inside rating");
      return this.http.post(apiRatingUrl, seriesRating, { withCredentials: true});
  }

  likeChapter(chapter) {
    return this.http.post(apiLikeUrl, chapter);
  }
}
