import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule} from '@angular/material/input';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatDialogModule} from '@angular/material/dialog';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatRadioModule} from '@angular/material/radio';
import {MatSelectModule} from '@angular/material/select';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule } from '@angular/forms';
import { MatProgressBarModule } from '@angular/material/progress-bar'; // Importa el módulo
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';




@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatRadioModule,
    MatSelectModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatInputModule,
    MatNativeDateModule,
    FormsModule,
    MatProgressBarModule,
    MatCardModule,
    MatProgressSpinnerModule,
    

  ],
  exports:[
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatRadioModule,
    MatSelectModule,
    MatSnackBarModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatInputModule,
    MatNativeDateModule,
    FormsModule,
    MatProgressBarModule,
    MatProgressSpinnerModule

  ]
})
export class MaterialModule { }
