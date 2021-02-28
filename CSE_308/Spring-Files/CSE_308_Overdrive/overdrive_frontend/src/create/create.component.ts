import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { CreateService } from './create.service';
import { Router } from '@angular/router';

@Component({
    selector: 'create',
    templateUrl: './create.component.html',
    styleUrls: ['./create.component.css']
})
export class CreateComponent {
    title = 'Create';
    genreOptions = ["Action", "Fantasy", "Comedy", "Drama", "Sports", "Horror"];
    constructor (private createService: CreateService, private router: Router) {
      this.createService = createService;
      this.router = router;
    }

    async ngAfterViewInit() {
        await this.loadScript('./src/js/main.js');
    }

    ngOnInit(){
      console.log("in ngOnInit");
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        })
    }

    comicForm = new FormGroup({
      comicSeriesName: new FormControl(''),
      genre: new FormControl(''),
      description: new FormControl(''),
      author: new FormControl('')
    })

    onSubmit(comicSeries) {
      console.log("in onSubmit");
      console.log(document.cookie);
      //comicSeries.author = document.cookie.split("=")[1];
      console.log(comicSeries);
      this.createService.createComicSeries(comicSeries)
        .subscribe( data => {
          console.log(data);
        });
      this.router.navigate(["/dashboard"]);
    }

}
