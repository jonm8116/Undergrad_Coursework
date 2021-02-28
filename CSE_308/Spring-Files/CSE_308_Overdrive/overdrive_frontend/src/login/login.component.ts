import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Users } from '../models/users/users';

// const httpOptions: {headers; observe;} = {
//   headers: new HttpHeaders({
//     'Content-Type':  'application/x-www-form-urlencoded',
//     'Authorization': 'my-auth-token'
//   }),
//   observe: 'response'
// };

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  ngOnInit() {
  }

  onSubmit(user) {

      console.log(user.username);
      console.log("Hit area for register");

      // var headers = new HttpHeaders();
      // headers.append('Content-Type', 'application/x-www-form-urlencoded');
      // console.log(" changes");

      // let payload = new HttpParams()
      //   .set('username', user.username)
      //   .set('password', user.password);

        console.log("before http call");
        // console.log(payload);
        document.cookie = "username="+ user.username;

       this.http.post<Users>("/api/login/loginuser", user)
        .subscribe( data => {
            console.log("inside login post request");
            // console.log(JSON.parse(data.value));



            document.cookie = "username="+ data.username;
            this.router.navigate(['/profile']);
             // this.http.get("http://localhost:8080/api/users/profile", {headers: headers, responseType: 'text'})
             //  .subscribe( data => {
             //      console.log("inside api/users/profile get request");
             //      console.log(data);
             //  });
        });
  }

}
