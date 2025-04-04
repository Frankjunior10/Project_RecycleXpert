import { Injectable } from "@angular/core";
import { UserService } from "../services/user.service";
import { ActivatedRouteSnapshot, GuardResult, MaybeAsync, RouterStateSnapshot } from "@angular/router";

@Injectable({
  providedIn:'root'
})
export class AutorizarVolunteerGuard{
  constructor (private userService: UserService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): 
  MaybeAsync<GuardResult>
// boolean | UrlTree | RedirectCommand | Observable<boolean | UrlTree | RedirectCommand> | Promise<boolean | UrlTree | RedirectCommand>
 {
   let permisos = this.userService.getAuthoritiesActual();
   if (permisos) {
     if (permisos.indexOf("VOLUNTARIO")>=0) {
       return true;
     }
   }
   return false;
 }

}