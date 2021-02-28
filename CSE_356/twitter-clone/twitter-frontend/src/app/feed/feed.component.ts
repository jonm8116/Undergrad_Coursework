import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

const apiRoute = 'http://130.245.170.128/additem';
const httpOptions: {headers; observe;} = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  }),
  observe: 'response'
};

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  ngOnInit() {
  }
  likeForm = new FormGroup({
    likes: new FormControl('')
  });

  tweetForm = new FormGroup({
      key: new FormControl(''),
      property: this.likeForm,
      retweeted: new FormControl(''),
      content: new FormControl(''),
      timestamp: new FormControl('')
  });

  onSubmit(verify){

    this.http.post(apiRoute, verify, httpOptions)
      .subscribe( data => {
          console.log(data);
      });
    this.router.navigate(["/show"]);
  }

}
