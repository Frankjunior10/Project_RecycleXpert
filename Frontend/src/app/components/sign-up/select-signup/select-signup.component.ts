import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { RegisterVolunteerComponent } from '../../register-volunteer/register-volunteer.component';

@Component({
  selector: 'app-select-signup',
  templateUrl: './select-signup.component.html',
  styleUrl: './select-signup.component.css'
})
export class SelectSignupComponent {
  step: 'choose' | 'volunteer' | 'organization' = 'choose'; // Estado del diálogo
  registerForm!: FormGroup;
  registerType: any;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<RegisterVolunteerComponent>,
    private router: Router,
    private dialog: MatDialog
  ) {

  }

  RegisterVolunteer():void {
      //console.log(this.registerForm.value); // Aquí puedes enviar los datos al backend
      this.router.navigate(['/volunteer-signup']);
      this.dialogRef.close();
  }

  onCancel(): void {
    this.router.navigate(['/home']);
  }


  RegisterOrganization() {
    //this.router.navigate(['/register-volunteer']);
    this.router.navigate(['/organization-signup']);
    this.dialogRef.close();
  }
}
