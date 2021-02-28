import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { SignupFormComponent } from './signup-form/signup-form.component';
import { FeedComponent } from './feed/feed.component';
import { LoginComponent } from './login/login.component';
import { VerifyComponent } from './verify/verify.component';

const routes: Routes = [
  { path: '', component: SignupComponent },
  { path: 'signupform', component: SignupFormComponent},
  { path: 'feed', component: FeedComponent},
  { path: 'login', component: LoginComponent},
  { path: 'verify', component: VerifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
