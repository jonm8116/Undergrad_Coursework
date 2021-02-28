import { Component } from '@angular/core';
import { ComicSeries } from '../models/comics/comic-series';
import { GenreService } from './genre.service';

@Component({
    selector: 'genre',
    templateUrl: './genre.component.html',
    styleUrls: ['./genre.component.css']
})
export class GenreComponent {
    title = 'Genre';

    constructor(private genreService: GenreService){
      this.genreService = genreService;
    }

    /* list out series lists for each genre */
    actionSeries: ComicSeries[];
    fantasySeries: ComicSeries[];
    comedySeries: ComicSeries[];
    sportsSeries: ComicSeries[];
    dramaSeries: ComicSeries[];
    horrorSeries: ComicSeries[];

    /*service results*/
    // serviceResults: ComicSeries[][];

    ngOnInit(){
      this.genreService.getGenreComics()
        .subscribe( genreResults => {
            this.actionSeries = genreResults[0];
            this.fantasySeries = genreResults[1];
            this.comedySeries = genreResults[2];
            this.dramaSeries = genreResults[3];
            this.sportsSeries = genreResults[4];
            this.horrorSeries = genreResults[5];

            let __this = this;
            this.actionSeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
            this.fantasySeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
            this.comedySeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
            this.dramaSeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
            this.sportsSeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
            this.horrorSeries.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });
        });
    }

    async ngAfterViewInit() {
        await this.loadScript('./src/js/genre.js');
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        })
    }


    scroll(el: HTMLElement) {
        el.scrollIntoView();
    }

    sendFollowRequest(btn, comic){
      this.genreService.followSeries(comic)
        .subscribe(data => {
          console.log(data);
        });
    }

    changeColor(tag){
      console.log("Hit changeColor function");
      console.log(tag);
      tag.children[0].style.cssText = "color: red;"
    }

    //method to call service to pass data to series page
    selectSeries(curSeries){
      this.genreService.selectSeries(curSeries);
    }

    truncate(string){
        if (string.length > 15)
            return string.substring(0,15)+'...';
        else
            return string;
    };

}
