import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './components/menu/menu.component';
import { RegisterVolunteerComponent } from './components/register-volunteer/register-volunteer.component';
import { EventRegisterComponent } from './components/event-register/event-register.component';
import { VolunteerListarComponent } from './components/volunteer-listar/volunteer-listar.component';
import { LoginComponent } from './components/login/login.component';
import { EventListarComponent } from './components/event-listar/event-listar.component';
import { HomeComponent } from './components/home/home.component';
import { VolunteerSignupComponent } from './components/sign-up/volunteer-signup/volunteer-signup.component';
import { OrganizationSignupComponent } from './components/sign-up/organization-signup/organization-signup.component';
import { EventRegistervolunteerComponent } from './components/event-registervolunteer/event-registervolunteer.component';
import { DonationRegisterComponent } from './components/donation-register/donation-register.component';


const routes: Routes = [

  {path: '', component: LoginComponent},
  {path: 'menu', component: MenuComponent},
  {path: 'home', component: HomeComponent},
  {path: 'register-volunteer', component: RegisterVolunteerComponent},
  {path: 'event-listar', component: EventListarComponent},
  {path: 'event-register', component: EventRegisterComponent},
  {path: 'volunteer-listar', component: VolunteerListarComponent},
  { path:"login", component: LoginComponent},
  { path:"volunteer-signup", component: VolunteerSignupComponent},
  { path:"organization-signup", component: OrganizationSignupComponent},
  { path:"event-registervolunteer", component: EventRegistervolunteerComponent},
  { path:"donation-register", component: DonationRegisterComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
