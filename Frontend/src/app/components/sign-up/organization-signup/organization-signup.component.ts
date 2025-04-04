import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { RegisterVolunteerComponent } from '../../register-volunteer/register-volunteer.component';
import { Router } from '@angular/router';
import { OrganizationService } from '../../../services/organization.service';
import { UserService } from '../../../services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User } from '../../../models/user';
import { Organization } from '../../../models/organization';

@Component({
  selector: 'app-organization-signup',
  templateUrl: './organization-signup.component.html',
  styleUrl: './organization-signup.component.css'
  
})
export class OrganizationSignupComponent {
  addForm!: FormGroup;
  ocultarPass: boolean = true;


  constructor(
    private organizationService: OrganizationService,
    private userService: UserService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private dialog: MatDialog

  /*
    id: number;                  // ID único de la organización
    organizationName: string;    // Nombre de la organización
    orgType: string;             // Tipo de organización (por ejemplo, NGO, Non-Profit)
    contactInfo: string;         // Información de contacto (email, teléfono, etc.)
    collaborationArea: string;
  */

  ) {
    this.addForm = this.fb.group({
      userName: ['', Validators.required],    
      password: ['', Validators.required],
      organizationName: ['', Validators.required],
      orgType: ['', Validators.required],
      contactInfo: ['', Validators.required],
      collaborationArea: ['', Validators.required],

    });
  }

  registrarOrganzation(): void {
    /*
    if (this.addForm.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }
         */
    if (this.addForm.invalid) {
      return;
    }

    const user: User = {
      id: 0,
      userName: this.addForm.get('userName')?.value,
      password: this.addForm.get('password')?.value,
      authorities: "ORGANIZACION"

    };

    /*
    id: number;                  // ID único de la organización
    organizationName: string;    // Nombre de la organización
    orgType: string;             // Tipo de organización (por ejemplo, NGO, Non-Profit)
    contactInfo: string;         // Información de contacto (email, teléfono, etc.)
    collaborationArea: string;
    */

    this.userService.newUser(user).subscribe({
      next: (data) => {
        const userId = data.id;

        const organization: Organization = { 
          id: 0,
          organizationName:this.addForm.get("organizationName")?.value,
          orgType:this.addForm.get("orgType")?.value,
          contactInfo:this.addForm.get("contactInfo")?.value,
          collaborationArea:this.addForm.get("collaborationArea")?.value,

        };

        this.organizationService.addOrganization(organization).subscribe({
          next: (data) => {
            this.snackBar.open("Organización registrada correctamente", "Ok", { duration: 3000 });
            this.router.navigate(['/login']); 
          },
          error: (err) => {
            this.snackBar.open("Error al registrar la Organización", "Ok", { duration: 3000 });
          }
        });
      },
      error: (err) => {
        this.snackBar.open("Error al registrar el usuario", "Ok", { duration: 3000 });
      }
    });
  }

  RegisterVolunteer():void {
      //console.log(this.registerForm.value); // Aquí puedes enviar los datos al backend
      this.router.navigate(['/register-volunteer']);
  }

  onCancel(): void {
    this.router.navigate(['/login']);
  }



}
