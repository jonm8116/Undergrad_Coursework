import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable({
  providedIn: 'root'
})
export class FeedService {

  constructor() { }

  private showSource = new BehaviorSubject(null);
  showTweet = this.showSource.asObservable();

  selectSeries(tweet){
    console.log("inside select series");
    this.showSource.next(tweet);
  }

}
