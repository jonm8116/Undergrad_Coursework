import { Component } from '@angular/core';
import { ComicSeries } from '../models/comics/comic-series';
import { PopularService } from './popular.service';
import { GenreService } from '../genre/genre.service';

@Component({
    selector: 'popular',
    templateUrl: './popular.component.html',
    styleUrls: ['./popular.component.css']
})
export class PopularComponent {
    title = 'Genre';

    constructor(private popularService: PopularService,
      private genreService: GenreService){
      this.popularService = popularService;
      this.genreService = genreService;
    }

    async ngAfterViewInit() {
        // await this.loadScript('./src/js/genre.js');
    }

    /* do popular by genre */
    popularAction: ComicSeries[];
    popularFantasy: ComicSeries[];
    popularDrama: ComicSeries[];
    popularComedy: ComicSeries[];
    popularSports: ComicSeries[];
    popularHorror: ComicSeries[];

    /* user selects particular comic series */
    selectSeries(curSeries){
      console.log("inside select Series");
      console.log(curSeries);
      this.genreService.selectSeries(curSeries);
    }

    ngOnInit(){
      this.popularService.getPopularComics()
        .subscribe( data => {
          this.popularAction = data[0];
          this.popularFantasy = data[1];
          this.popularDrama = data[2];
          this.popularComedy = data[3];
          this.popularSports = data[4];
          this.popularHorror = data[5];
        })
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
}
