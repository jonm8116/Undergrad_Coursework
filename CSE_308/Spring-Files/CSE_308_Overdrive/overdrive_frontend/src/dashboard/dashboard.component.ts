import { Component } from '@angular/core';
import { DashboardService } from './dashboard.service';
import { ComicSeries } from '../models/comics/comic-series';

@Component({
    selector: 'dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
    title = 'Genre';
    /* List of user comics for dashboard*/
    comics: ComicSeries[];

    constructor(private dashboardService: DashboardService){
      this.dashboardService = dashboardService;
    }

    ngOnInit(){
      this.populateComics();
    }

    async ngAfterViewInit() {
        await this.loadScript('./src/js/main.js');
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        })
    }

    updateCurrentSeries(series){
      this.dashboardService.updateCurrentSeries(series);
    }

    populateComics(){
      this.dashboardService.getUserComics()
        .subscribe( data => {
          console.log(data);
          this.comics = data;
            let __this = this;
            this.comics.forEach(function(series) {
                series.comicSeriesName = __this.truncate(series.comicSeriesName);
            });

            console.log(this.comics, "DATA");
        });
    }

    truncate(string){
        if (string.length > 15)
            return string.substring(0,15)+'...';
        else
            return string;
    };
}
