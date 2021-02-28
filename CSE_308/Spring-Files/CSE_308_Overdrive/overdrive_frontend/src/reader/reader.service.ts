import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';

const likeChpUrl = '/api/series/chapter/like';
const getCommentUrl = '/api/series/chapter/listcomments/';
const postCommentUrl = '/api/series/chapter/addComment';
const getChapImgsUrl = '/api/series/chapter/view/publish/';
const userLikedChapUrl = '/api/series/chapter/liked/';

@Injectable({
  providedIn: 'root'
})
export class ReaderService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  likeChapter(chapterId){
    console.log("inside readerService likeChapter");
    console.log(chapterId);
    return this.http.post(likeChpUrl, chapterId, {withCredentials: true});
  }

  postComment(comment){
    return this.http.post(postCommentUrl, comment, {withCredentials: true});
  }

  getComments(chapId){
    return this.http.get(getCommentUrl + chapId);
  }
  getChapter(seriesId, chapNum){
      return this.http.get(getChapImgsUrl + seriesId + "/" + chapNum);
  }

  hasUserLikedChapter(chapId) {
      return this.http.get(userLikedChapUrl + chapId, {withCredentials: true});
  }
}
