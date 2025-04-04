import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Token } from '../models/token';
import { Volunteer } from '../models/volunteer';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //Prueba
  private currentUserRole: string | null = null;
  private currentUserSubject: BehaviorSubject<User | null> = new BehaviorSubject<User | null>(null);

  //
  ruta_servidor: string = "http://localhost:8080/api";
  recurso:string = "users";


  constructor(private http:HttpClient) { 
    const authorities = this.getAuthoritiesActual();
    if (authorities) {
      this.setRole(authorities);
    }
  }

  //Prueba
  setRole(authorities: string): void {
    this.currentUserRole = authorities;
  }

  // Cambiados de getters a métodos regulares
  isVolunteer(): boolean {
    const authorities = this.getAuthoritiesActual();
    return authorities?.includes('VOLUNTARIO') ?? false;
  }

  isOrganization(): boolean {
    const authorities = this.getAuthoritiesActual();
    return authorities?.includes('ORGANIZACION') ?? false;
  }


  getUser(id: number){
    return this.http.get<User>(this.ruta_servidor+"/"+this.recurso+"/"+id.toString());
  }

  newUser(user: User){
    return this.http.post<User>(this.ruta_servidor+"/"+this.recurso + "/" + "register",user);
  }

  login(user: User) {
    this.logout();
    return this.http.post<Token>(this.ruta_servidor + "/" + this.recurso + "/" + "login", user).pipe(
      tap((resultado: Token) => {
        localStorage.setItem('jwtToken', resultado.jwtToken);
        localStorage.setItem('user_id', resultado.user_id.toString());
        localStorage.setItem('authorities', resultado.authorities);
        
        // Establecer el rol inmediatamente después del login
        this.setRole(resultado.authorities);

        // Obtener usuario después de login
        this.getUser(Number(resultado.user_id)).subscribe((user) => {
          this.currentUserSubject.next(user);
        });
      })
    );
  }
  
  get currentUser() {
    return this.currentUserSubject.asObservable();
  }

  logout() {
    if (typeof localStorage !== 'undefined') {
      localStorage.clear();
    }
    this.currentUserRole = null;
    this.currentUserSubject.next(null);
  }

  hayUsuarioLogeado(){
    if (this.getUserIdActual() == null || this.getUserIdActual() == "") {
      return false;
    }
    return true;
  }

  getTokenActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('jwtToken');  
    } 
    return null;
  }

  getUserIdActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('user_id');
    }
    return null;

  }

  getAuthoritiesActual() {
    if (typeof localStorage !== 'undefined') {
      return localStorage.getItem('authorities');
    }
    return null;
  }

  getNombreUsuarioLogeado(): string {
    const userId = this.getUserIdActual();
    if (userId) {
      this.getUser(Number(userId)).subscribe((user) => {
        return user.userName; 
      });
    }
    return '';
  }

}
