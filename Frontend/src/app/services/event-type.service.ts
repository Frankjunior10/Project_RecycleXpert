import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Evento } from '../models/evento';
import { EventType } from '../models/eventType';

@Injectable({
  providedIn: 'root'
})
export class EventTypeService {
  // Ruta a donde se conectará
  servidor: string = "http://localhost:8080/api";
  recurso: string = "eventypes";

  constructor(private http:HttpClient) { }
  
  getEventTypes(){
    return this.http.get<EventType[]>(this.servidor+"/"+this.recurso);
  }
  getEventType(id: number){
    return this.http.get<EventType>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }
  
}
