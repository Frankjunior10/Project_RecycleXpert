import { Component, OnInit } from '@angular/core';
import { VolunteerService } from '../../services/volunteer.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  volunteerPoints: number = 120; // Estos son puntos actuales del voluntario
  maxPoints: number = 200; // Estos son puntos máximos necesarios para subir al siguiente nivel
  isVolunteer: boolean = false;
  isOrganization: boolean = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.isVolunteer = this.userService.isVolunteer();
    this.isOrganization = this.userService.isOrganization();
  }

  // Función para calcular el progreso en porcentaje
  calculateProgress(points: number): number {
    return (points / this.maxPoints) * 100;
  }

  // Función para calcular los puntos faltantes para el siguiente nivel
  calculateRemainingPoints(points: number): number {
    return this.maxPoints - points > 0 ? this.maxPoints - points : 0;
  }


}
