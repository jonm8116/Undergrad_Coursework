import { Component, OnInit } from '@angular/core';
import { GenreService } from '../genre/genre.service';
import { SeriesService } from './series.service';
import { ComicSeries } from '../models/comics/comic-series';
import { ComicChapter } from '../models/comics/comic-chapter';
import { DatePipe } from '@angular/common'
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-series',
  templateUrl: './series.component.html',
  styleUrls: ['./series.component.css']
})
export class SeriesComponent implements OnInit {

  constructor(private genreService: GenreService, private seriesService: SeriesService,
              public datepipe: DatePipe, private router: Router) {
    this.genreService = genreService;
    this.seriesService = seriesService;
    this.router = router;
  }

  currentSeries: ComicSeries;
  seriesChapters: ComicChapter[];
  numOfChapters: number = 0;

  userSeriesScore : number;

  ngOnInit() {
    this.genreService.selectedSeries
      .subscribe( data => {
        console.log("inside series page genreService call");
        console.log(data);
        /*  store data in local storage */
        if(data){
          console.log("inside if data");
          this.currentSeries = data;
          this.rateStar(null, Math.round(this.currentSeries.score));
          localStorage.removeItem('currentSeries');
          localStorage.setItem('currentSeries', JSON.stringify(data));

          this.seriesService.getSeriesChapters(data.seriesId)
            .subscribe( data => {
              console.log("inside getSeriesChapters");
              console.log(data);
              this.seriesChapters = data;
              for(var i=0; i<this.seriesChapters.length; i++){
                this.seriesChapters[i].chapterNumber = i+1;
              }
            });

        } else{
          console.log("outside if data");
          console.log(JSON.parse(localStorage.getItem('currentSeries')));
          this.currentSeries = JSON.parse(localStorage.getItem('currentSeries'));

          this.seriesService.getSeriesChapters(this.currentSeries.seriesId)
            .subscribe( data => {
              console.log("inside getSeriesChapters");
              console.log(data);
              this.seriesChapters = data;
              for(var i=0; i<this.seriesChapters.length; i++){
                this.seriesChapters[i].chapterNumber = i+1;
              }
            });
        }
      });
  }

    seriesRating = new FormGroup({
        author: new FormControl(''),
        comicSeriesName: new FormControl(''),
        score: new FormControl('')
    });




    formatDate(date_string){
        let date = new Date(date_string);
        let latest_date =this.datepipe.transform(date, 'yyyy-MM-dd');
        return latest_date;
    }

    getFormattedScore(score) {
        return  Math.round(score * 100) / 100;
    }

    rateStar(e, score) {
        for(let i = 1; i <= 5; i++) {
            let elem = document.getElementById('star_' + i);
            elem.className = 'fa fa-star';
        }

        for(let i = 1; i <= score; i++) {
            let elem = document.getElementById('star_' + i);
            elem.className = 'fa fa-star checked';
        }

        this.userSeriesScore = score;
        this.seriesRating.setValue({
            author: this.currentSeries.author,
            comicSeriesName: this.currentSeries.comicSeriesName,
            score: this.userSeriesScore
        });
        console.log("inside rateStar");
        console.log(this.seriesRating.value);

        this.seriesService.setSeriseRating(this.seriesRating.value)
            .subscribe( data => {
                console.log("inside set series rating subscribe");
                console.log(data);
            });
    }
    goToReader(chapterNumber){
      this.router.navigate(['/reader/'+this.currentSeries.seriesId+"/"+chapterNumber]);
    }

    sendFollowRequest(comic){
      console.log("inside sendFollowRequest");
      console.log("hit send follow method");
      this.genreService.followSeries(comic)
        .subscribe(data => {
          console.log(data);
        });
    }
}
