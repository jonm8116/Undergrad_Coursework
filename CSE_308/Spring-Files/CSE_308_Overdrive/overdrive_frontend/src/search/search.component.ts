import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SearchService } from './search.service';
import { ComicSeries } from '../models/comics/comic-series';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private searchService: SearchService) {
    this.activatedRoute = activatedRoute;
    this.searchService = searchService;
  }

  /* current search query */
  searchQuery: string;
  searchResults: ComicSeries[];

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(
      data => {
        this.searchQuery = data['query'];
        let searchQuery = data['query'];
        this.searchService.getSearchResults(searchQuery)
          .subscribe( data => {
            this.searchResults = data;
          });
      }
    );
  }



}
