import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VolunteerService } from '../../services/volunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-register-volunteer',
  templateUrl: './register-volunteer.component.html',
  styleUrl: './register-volunteer.component.css'
})
export class RegisterVolunteerComponent {
  addForm! : FormGroup;

  constructor(
    private volunteerService: VolunteerService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.CrearFormulario();
  }

  CrearFormulario(){
    this.addForm = this.fb.group({
        id:[""],
        volunteerName: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        address: ['', Validators.required],
        points: [0, [Validators.required, Validators.min(0)]],
        category: ['', Validators.required],
        level: [1, [Validators.required, Validators.min(1)]]
      });
  }
  registrarVoluntario(): void {
    if (this.addForm.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }
  
    const volunteer = { 
      id: 0,
      volunteerName: this.addForm.get("volunteerName")?.value,
      email: this.addForm.get("email")?.value,
      address: this.addForm.get("address")?.value,
      points: this.addForm.get("points")?.value,
      category: this.addForm.get("category")?.value,
      level: this.addForm.get("level")?.value  
    };
  
    // Llamada al servicio para registrar al voluntario
    this.volunteerService.addVolunteer(volunteer).subscribe({
      next: (data) => {
        this.snackBar.open("Voluntario registrado correctamente", "Ok", { duration: 3000 });
        this.router.navigate(['/volunteer-listar']); // Redirigir a la lista de voluntarios
      },
      error: (err) => {
        this.snackBar.open("Error al registrar el voluntario", "Ok", { duration: 3000 });
      }
    });
  }
  
  onCancel(): void {
    this.router.navigate(['/login']);
  }

}
