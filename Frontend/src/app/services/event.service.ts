import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Evento } from '../models/evento';

@Injectable({
  providedIn: 'root'
})
export class EventService {

  // Ruta a donde se conectará
  servidor: string = "http://localhost:8080/api";
  recurso: string = "events";

  constructor(private http: HttpClient) { }

  // Para listar eventos, con soporte para filtrar por usuario
  getEvents(userId?: number) {
    let url = this.servidor + "/" + this.recurso;
    if (userId) {
      url += `?userId=${userId}`; // Agregar el filtro como query param
    }
    return this.http.get<Evento[]>(url);
  }

  // Para buscar un evento específico por ID
  getEvent(id: number) {
    return this.http.get<Evento>(this.servidor + "/" + this.recurso + "/" + id.toString());
  }

  // Para eliminar un evento
  deleteEvent(id: number) {
    return this.http.delete<Evento>(this.servidor + "/" + this.recurso + "/" + id.toString());
  }

  // Para añadir o insertar un nuevo evento
  addEvento(evento: Evento) {
    return this.http.post<Evento>(this.servidor + "/" + this.recurso, evento);
  }

  // Para editar un evento existente
  editEvent(evento: Evento) {
    return this.http.put<Evento>(this.servidor + "/" + this.recurso + "/" + evento.id.toString(), evento);
  }
}
