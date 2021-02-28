import { Component } from '@angular/core';
import { DiscoverService } from './discover.service';
import { ComicSeries } from '../models/comics/comic-series';

@Component({
    selector: 'discover',
    templateUrl: './discover.component.html',
    styleUrls: ['./discover.component.css']
})
export class DiscoverComponent {
    title = 'Genre';

    constructor(private discoverService: DiscoverService){
      this.discoverService = discoverService;
    }

    /*  user's recommeneded comics  */
    discoverComics: ComicSeries[];

    ngOnInit(){
      this.discoverService.getDiscoverSeries()
        .subscribe( data => {
          this.discoverComics = data;
        });
    }

    async ngAfterViewInit() {
        // await this.loadScript('./src/js/genre.js');
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        })
    }


}
