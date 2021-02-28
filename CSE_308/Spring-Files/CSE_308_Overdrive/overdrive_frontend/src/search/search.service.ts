import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders } from '@angular/common/http';
import { ComicSeries } from '../models/comics/comic-series';

const apiSearchUrl = '/api/series/search'

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) {
    this.http = http;
  }

  getSearchResults(searchQuery){
    return this.http.get<ComicSeries[]>(apiSearchUrl+"/"+searchQuery);
  }
}
