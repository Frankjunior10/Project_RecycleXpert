import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DonationDTO } from '../models/donation';

@Injectable({
  providedIn: 'root'
})
export class DonationService {
  private servidor: string = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  // Crear una donación
  addDonation(donation: DonationDTO): Observable<DonationDTO> {
    return this.http.post<DonationDTO>(`${this.servidor}/donations`, donation);
  }

  // Obtener todas las donaciones de un evento
  getDonationsForEvent(eventId: number): Observable<DonationDTO[]> {
    return this.http.get<DonationDTO[]>(`${this.servidor}/donations/event/${eventId}`);
  }

  // Obtener una donación por su ID
  getDonation(donationId: number): Observable<DonationDTO> {
    return this.http.get<DonationDTO>(`${this.servidor}/donations/${donationId}`);
  }

  // Editar una donación (si implementas esta funcionalidad)
  editDonation(donationId: number, donation: DonationDTO): Observable<DonationDTO> {
    return this.http.put<DonationDTO>(`${this.servidor}/donations/${donationId}`, donation);
  }

  // Eliminar una donación por su ID
  deleteDonation(donationId: number): Observable<void> {
    return this.http.delete<void>(`${this.servidor}/donations/${donationId}`);
  }
}
