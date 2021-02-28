import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Users } from '../models/users';

const apiRoute= '/api/verify';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) {
    this.http = http;
    this.router = router;
  }

  ngOnInit() {
  }



  verifyForm = new FormGroup({
      key: new FormControl(''),
      email: new FormControl('')
  });

  onSubmit(verify){
    this.http.post<Users>(apiRoute, verify)
     .subscribe( data => {
         console.log(data);
         var token = document.cookie.split("=")[1];
         this.router.navigate(['/feed']);
     });
  }

}
