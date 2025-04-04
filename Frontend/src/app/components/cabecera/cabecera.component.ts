import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../../models/user';

@Component({
  selector: 'app-cabecera',
  templateUrl: './cabecera.component.html',
  styleUrls: ['./cabecera.component.css']
})
export class CabeceraComponent implements OnInit {

  nombreUsuario: string = '';
  currentUser$: Observable<User | null>;

  constructor(private userService: UserService, private router: Router) {
    
    this.currentUser$ = this.userService.currentUser;
  }

  ngOnInit(): void {
    if (this.usuarioLogeado()) {
      const userId = this.userService.getUserIdActual();
      if (userId) {
        this.userService.getUser(Number(userId)).subscribe((user) => {
          this.nombreUsuario = user.userName;
        });
      }
    }
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['/login']);
  }

  usuarioLogeado() {
    return this.userService.hayUsuarioLogeado();
  }

  verInformacionUsuario() {
    this.router.navigate(['/usuario/informacion']);
  }
}
