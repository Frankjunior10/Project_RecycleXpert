import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { VolunteerService } from '../../services/volunteer.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Volunteer } from '../../models/volunteer';

@Component({
  selector: 'app-edit-volunteer',
  templateUrl: './edit-volunteer.component.html',
  styleUrls: ['./edit-volunteer.component.css']
})
export class EditVolunteerComponent implements OnInit {
  editForm!: FormGroup;
  volunteerId!: number;

  constructor(
    private fb: FormBuilder,
    private volunteerService: VolunteerService,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.volunteerId = Number(this.activatedRoute.snapshot.paramMap.get('id'));
    this.CrearFormulario();
    this.loadVolunteerData();
  }

  CrearFormulario(): void {
    this.editForm = this.fb.group({
      id: [''],
      volunteerName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      points: [0, [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      level: [1, [Validators.required, Validators.min(1)]]
    });
  }

  loadVolunteerData(): void {
    this.volunteerService.getVolunteerById(this.volunteerId).subscribe({
      next: (volunteer: Volunteer) => {
        this.editForm.patchValue(volunteer);
      },
      error: (err) => {
        this.snackBar.open("Error al cargar los datos del voluntario", "Ok", { duration: 3000 });
      }
    });
  }

  editarVoluntario(): void {
    if (this.editForm.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }

    const updatedVolunteer = { 
      id: this.editForm.get("id")?.value,
      volunteerName: this.editForm.get("volunteerName")?.value,
      email: this.editForm.get("email")?.value,
      address: this.editForm.get("address")?.value,
      points: this.editForm.get("points")?.value,
      category: this.editForm.get("category")?.value,
      level: this.editForm.get("level")?.value  
    };

    this.volunteerService.editVolunteer(updatedVolunteer).subscribe({
      next: () => {
        this.snackBar.open("Voluntario actualizado correctamente", "Ok", { duration: 3000 });
        this.router.navigate(['/volunteer-listar']);
      },
      error: (err) => {
        this.snackBar.open("Error al actualizar el voluntario", "Ok", { duration: 3000 });
      }
    });
  }

  onCancel(): void {
    this.router.navigate(['/volunteer-listar']);
  }
}

