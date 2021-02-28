import { Component } from '@angular/core';
import { MainNavService } from './main-nav.service';
import { Router } from '@angular/router';

@Component({
    selector: 'main-nav',
    templateUrl: './main-nav.component.html',
    styleUrls: ['./main-nav.component.css']
})
export class MainNavComponent {
    title = 'overdrive-comics';

    constructor(private mainNavService: MainNavService, private router: Router){
      this.mainNavService = mainNavService;
      this.router = router;
    }

    ngOnInit(){

    }
    currentLink: string;
    currentUser = document.cookie.split("=")[1];

    onEnter(searchText){
      // console.log("Value of searchText is: " + searchText);
      this.router.navigate(['/search'], {queryParams: {query: searchText}});
    }

}
