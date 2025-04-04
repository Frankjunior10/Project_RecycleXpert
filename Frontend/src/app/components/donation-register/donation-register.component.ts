import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DonationService } from '../../services/donation.service';
import { EventService } from '../../services/event.service'; // Asegúrate de tener este servicio
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { DonationDTO } from '../../models/donation';
import { Evento } from '../../models/evento'; // Modelo para eventos

@Component({
  selector: 'app-donation-register',
  templateUrl: './donation-register.component.html',
  styleUrls: ['./donation-register.component.css']
})
export class DonationRegisterComponent implements OnInit {
  donationForm: FormGroup;
  userId: number | null = 2; // ID del voluntario (temporal)
  events: Evento[] = []; // Lista de eventos

  constructor(
    private fb: FormBuilder,
    private donationService: DonationService,
    private eventService: EventService, // Servicio para obtener eventos
    private snack: MatSnackBar,
    private router: Router
  ) {
    this.donationForm = this.fb.group({
      donorName: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(1)]],
      eventId: ['', Validators.required] // Agrega validación para el evento
    });
  }

  ngOnInit(): void {
    this.loadEvents();
  }

  // Método para cargar eventos desde el servicio
  loadEvents(): void {
    this.eventService.getEvents().subscribe({
      next: (data: Evento[]) => {
        this.events = data;
      },
      error: (err) => {
        console.error('Error al cargar eventos:', err);
        this.snack.open('Error al cargar eventos. Intenta nuevamente.', 'OK', { duration: 2000 });
      }
    });
  }

  submitDonation(): void {
    if (this.donationForm.invalid) {
      this.snack.open('Por favor, completa todos los campos requeridos.', 'OK', { duration: 2000 });
      return;
    }

    const donationData: DonationDTO = {
      donorName: this.donationForm.value.donorName,
      amount: this.donationForm.value.amount,
      eventId: this.donationForm.value.eventId, // Ahora se obtiene del formulario
      volunteerId: this.userId!,
      donationDate: new Date().toISOString() // Formato ISO
    };
    
    this.donationService.addDonation(donationData).subscribe({
      next: (response) => {
        this.snack.open('Donación registrada exitosamente.', 'OK', { duration: 2000 });
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Error al registrar la donación:', err);
        this.snack.open('Error al registrar la donación. Intenta nuevamente.', 'OK', { duration: 2000 });
      }
    });
  }

  // Método para cancelar
  cancel(): void {
    this.router.navigate(['/home']); // Redirige al home o donde corresponda
  }
}
