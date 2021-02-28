import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'overdrive-comics';


    async ngAfterViewInit() {
        await this.loadScript('./src/js/main.js');
        await this.loadScript('./src/js/genre.js');
        await this.loadScript('https://cdn.commento.io/js/commento.js');
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
