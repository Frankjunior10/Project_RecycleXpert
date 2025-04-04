import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VolunteerService } from '../../../services/volunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user.service';
import { User } from '../../../models/user';
import { Volunteer } from '../../../models/volunteer';

@Component({
  selector: 'app-volunteer-signup',
  templateUrl: './volunteer-signup.component.html',
  styleUrls: ['./volunteer-signup.component.css']
})
export class VolunteerSignupComponent {
  addForm!: FormGroup;
  ocultarPass: boolean = true;


  constructor(
    private volunteerService: VolunteerService,
    private userService: UserService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router
  ) {
    this.addForm = this.fb.group({
      userName: ['', Validators.required],         // Para User
      password: ['', Validators.required],         // Para User
      volunteerName: ['', Validators.required],    // Para Volunteer
      email: ['', [Validators.required, Validators.email]],   // Para Volunteer
      address: ['', Validators.required],          // Para Volunteer
    });
  }

  registrarVoluntario(): void {
    
    if (this.addForm.invalid) {
      return;
    } 

    const user: User = {
      id: 0,
      userName: this.addForm.get('userName')?.value,
      password: this.addForm.get('password')?.value,
      authorities: "VOLUNTARIO"
    };

    this.userService.newUser(user).subscribe({
      next: (data) => {
        const userId = data.id;

        const volunteer: Volunteer = { 
          id: 0,
          volunteerName: this.addForm.get("volunteerName")?.value,
          email: this.addForm.get("email")?.value,
          address: this.addForm.get("address")?.value,
          points: this.addForm.get("points")?.value,
          category: this.addForm.get("category")?.value,
          level: this.addForm.get("level")?.value  
          
        };

        this.volunteerService.addVolunteer(volunteer).subscribe({
          next: (data) => {
            this.snackBar.open("Voluntario registrado correctamente", "Ok", { duration: 3000 });
            this.router.navigate(['/login']); 
          },
          error: (err) => {
            this.snackBar.open("Error al registrar el voluntario", "Ok", { duration: 3000 });
          }
        });
      },
      error: (err) => {
        this.snackBar.open("Error al registrar el usuario", "Ok", { duration: 3000 });
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/login']);
  }
}
