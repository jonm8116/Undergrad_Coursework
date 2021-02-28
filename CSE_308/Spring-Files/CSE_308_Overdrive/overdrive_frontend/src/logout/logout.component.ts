import { Component, OnInit } from '@angular/core';
import { LogoutService } from './logout.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private logoutService: LogoutService, private router: Router) {
    this.logoutService = logoutService;
    this.router = router;
  }

  ngOnInit() {
    this.logoutUserAndCookie();
    this.router.navigate(['/register']);
  }

  logoutUserAndCookie(){
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
    this.logoutService.callLogoutUser()
      .subscribe( data => {
        this.router.navigate(['/register']);
      });
  }
}
