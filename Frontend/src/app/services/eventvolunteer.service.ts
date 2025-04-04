import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventvolunteerService {
  unsubscribeVolunteerFromEvent(volunteerId: string, eventId: number) {
    throw new Error('Method not implemented.');
  }
    // Ruta a donde se conectará
    servidor: string = "http://localhost:8080/api";
    recurso: string = "registerevent";

  constructor(private http: HttpClient) {  }

    // Obtener todos los eventos disponibles
    getAllEvents(): Observable<any[]> {
      return this.http.get<any[]>(`${this.servidor}/registerevent`);
    }
  
    // Registrar un voluntario en un evento
    registerVolunteerToEvent(volunteerId: number, eventId: number): Observable<any> {
      return this.http.post<any>(`${this.servidor}/registerevent`, { eventId, volunteerId });
    }
}
