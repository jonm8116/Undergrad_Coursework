import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

const apiRoute = '/api/adduser';

const httpOptions: {headers; observe;} = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  }),
  observe: 'response'
};

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  ngOnInit() {
  }

  userForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      email: new FormControl('')
  });

  onSubmit(user){
    this.http.post<Users>(apiRoute, user)
     .subscribe( data => {
         console.log(data);
         this.router.navigate(['/verify']);
     });

  }

}
