import { CanActivateFn } from '@angular/router';

export const autorizarLogeadoGuard: CanActivateFn = (route, state) => {
  return true;
};
import { Injectable } from "@angular/core";
import { UserService } from "../services/user.service";
import { ActivatedRouteSnapshot, GuardResult, MaybeAsync, RouterStateSnapshot } from "@angular/router";

@Injectable({
  providedIn:'root'
})
export class AutorizarLogeadoGuard{
  constructor (private userService: UserService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): 
  MaybeAsync<GuardResult>
// boolean | UrlTree | RedirectCommand | Observable<boolean | UrlTree | RedirectCommand> | Promise<boolean | UrlTree | RedirectCommand>
 {
  return this.userService.hayUsuarioLogeado();
 }

}