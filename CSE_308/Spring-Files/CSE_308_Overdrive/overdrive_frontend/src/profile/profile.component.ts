import { Component } from '@angular/core';
import { Profile } from './profile';
import { Users } from '../models/users/users';
import { ProfileService } from './profile.service';
import { ComicSeries } from '../models/comics/comic-series'
import { GenreService } from '../genre/genre.service';

@Component({
    selector: 'profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
    title = 'profile';

    /*In order to refer to service with 'this' dependency inject as private field*/
    constructor(private profileService: ProfileService, private genreService: GenreService){
      this.profileService = profileService;
      this.genreService = genreService;
    }

    /* Current logged in user */
    currentUser: Users;
    /* Series the user follows */
    followedSeries: ComicSeries[];
    /* Series the user created */
    createdSeries: ComicSeries[];
    /* User total likes */
    userTotalLikes: any;
    /* User total followers*/
    userTotalFollowers: any;
    /* User total comics*/
    userTotalComics: any;
    /* User total follows*/
    userTotalFollows: any;

    async ngAfterViewInit() {
        await this.loadScript('./src/js/main.js');
        await this.loadScript('./src/js/genre.js');
    }

    ngOnInit(){
      console.log("inside ngOnInit");
      this.populatePage();
      this.displayFollowedSeries();
      this.displayCreatedSeries();
      this.displayTotalLikes();
      this.displayTotalComics();
      this.displayTotalFollows();
      this.displayTotalFollowers();
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        });
    }

    populatePage(){
      console.log("in populate page");
      this.profileService.getUserDetails()
      .subscribe( data => {
        console.log("inside get request: PROFILE");
        console.log(data);
        this.currentUser = data;
      });
    }

    displayFollowedSeries(){
      console.log("in followed series");
      this.profileService.getFollowedSeries()
      .subscribe(data => {
        console.log("inside post request: displayfollows");
        console.log(data);
        this.followedSeries = data;
          let __this = this;
          this.followedSeries.forEach(function(series) {
              series.comicSeriesName = __this.truncate(series.comicSeriesName);
          });
      });
    }

    displayCreatedSeries(){
      console.log("in followed series");
      this.profileService.getCreatedSeries()
      .subscribe(data => {
        console.log("inside post request: display created");
        console.log(data);
        this.createdSeries = data;
          let __this = this;
          this.createdSeries.forEach(function(series) {
              series.comicSeriesName = __this.truncate(series.comicSeriesName);
          });
      });
    }

    scroll(el: HTMLElement) {
        el.scrollIntoView();
    }

    displayTotalLikes(){
      console.log("in display total likes");
      this.profileService.getUserTotalLikes()
        .subscribe( data => {
          console.log("inside profile service total likes");
          this.userTotalLikes = data;
        });
    }

    displayTotalFollowers(){
      console.log("in display total followers");
      this.profileService.getUserTotalFollowers()
        .subscribe( data => {
          console.log("inside display total followers");
          this.userTotalFollowers = data;
        });
    }

    displayTotalComics(){
      console.log("in display total comics");
      this.profileService.getUserTotalComics()
        .subscribe( data => {
          console.log("inside profile service display total comics");
          this.userTotalComics = data;
        });
    }

    displayTotalFollows(){
      console.log("in display total follows");
      this.profileService.getUserTotalFollows()
        .subscribe( data => {
          console.log("inside profile service display total follows");
          this.userTotalFollows = data;
        });
    }

    /* user selects particular comic series */
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
