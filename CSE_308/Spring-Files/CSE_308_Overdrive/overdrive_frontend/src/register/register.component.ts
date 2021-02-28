import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

const httpOptions: {headers; observe;} = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  }),
  observe: 'response'
};

@Component({
    selector: 'register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})

export class RegisterComponent {
    title = 'Create';

    constructor (private http: HttpClient, private router: Router) {
      this.http = http;
      this.router = router;
    }

    //Describe FormGroup for user
    userForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      password2: new FormControl(''),
      email: new FormControl('')
    });


    onSubmit(user) {
        console.log(user);
        console.log("Hit area for register");
        this.http.post("/api/users/register", user, httpOptions)
          .subscribe( data => {
              console.log(data);
          });
        this.router.navigate(["/login"]);

    }


}
