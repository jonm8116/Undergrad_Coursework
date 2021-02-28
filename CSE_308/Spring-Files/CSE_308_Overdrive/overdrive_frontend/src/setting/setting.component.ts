import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { SettingService } from './setting.service';
import {Users} from "../models/users/users";


@Component({
    selector: 'setting',
    templateUrl: './setting.component.html',
    styleUrls: ['./setting.component.css']
})
export class SettingComponent {
    title = 'profile';
    currentUser: Users;
    public userFile: any = File;

    constructor(private settingService: SettingService){
      this.settingService = settingService;
    }

    async ngAfterViewInit() {
        await this.loadScript('./src/js/main.js');
        this.populatePage();
    }

    private loadScript(scriptUrl: string) {
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = scriptUrl;
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
        })
    }

    populatePage(){
        this.settingService.getUserDetails()
            .subscribe( data => {
                console.log("inside get request: PROFILE");
                console.log(data);
                this.currentUser = data;
                (<HTMLInputElement>document.getElementById('bio-input')).value = this.currentUser.bio;
            });
    }

    userPasswordForm = new FormGroup({
      password: new FormControl(''),
      secondPassword: new FormControl(''),
      username: new FormControl(document.cookie.split("=")[1])
    });

    userDisplayNameForm = new FormGroup({
      username: new FormControl('')
    });

    userBioForm = new FormGroup({
      bio: new FormControl(''),
      username: new FormControl(document.cookie.split("=")[1])
    });

    updateBio(userBio){
      this.settingService.updateBio(userBio)
        .subscribe( data => {
          console.log("updateBio subscribe");
          console.log(data);
            if(data == true) {
                this.showAlert('bio-alert', 'success', 'Updated profile bio');
            }else {
                this.showAlert('bio-alert', 'alert', 'Error updating profile bio');
            }
        });
    }

    updateDisplayName(displayName){
      // displayName.username = document.cookie.split("=")[1];
      console.log("in updateDisplayName");
      console.log(displayName.username);
      document.cookie = "username=" + displayName.username;
      this.settingService.updateDisplayName(displayName)
        .subscribe( data => {
          console.log("updateDisplayName subscribe");
          console.log(data);
            if(data == true) {
                this.showAlert('username-alert', 'success', 'Updated username');
            }else {
                this.showAlert('username-alert', 'alert', 'Error updating username');
            }
        });
    }

    showAlert(id, type, msg) {

        if(document.getElementById(id).innerText == msg) {
            if(document.getElementById(id).style.display  != 'none') {
                return;
            }
        }

        let current_alert = document.getElementById(id);
        current_alert.className = "";

        current_alert.className = "alert-box " + type;
        current_alert.innerText = msg;
        current_alert.style.display = "block";
    }

    updateUserPassword(userPass){
      console.log(userPass);
      if(userPass.password == userPass.secondPassword){
        this.settingService.updatePassword(userPass)
          .subscribe( data => {
            console.log("updateUserPassword subscribe");
            console.log(data);
              if(data == true) {
                  this.showAlert('password-alert', 'success', 'Updated password');
              }else {
                  this.showAlert('password-alert', 'alert', 'Error updating password');
              }
          });
        console.log("passwords are same");
      } else{
        this.showAlert('password-alert', 'alert', "Passwords are not the same");
      }
    }

    onSelectFile(event){
      const file = event.target.files[0];
      console.log(file);
      this.userFile = file;
    }

    updatePic(){
      const formData = new FormData();
      formData.append("pic", this.userFile);
      this.settingService.updateProfilePic(formData)
      .subscribe(data => {
        console.log(data);
          if(data == true) {
              this.showAlert('image-alert', 'success', 'Updated profile picture');
          }else {
              this.showAlert('image-alert', 'alert', 'Error updating profile picture');
          }
      });
    }

    scroll(el: HTMLElement) {
        el.scrollIntoView();
    }


}
