import { Injectable } from '@angular/core';
import { Volunteer } from '../models/volunteer';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VolunteerService {
    // Ruta a donde se conectará
    servidor: string = "http://localhost:8080/api";
    recurso: string = "volunteers";

  constructor(private http: HttpClient) {}

  // Para mostrar en la Lista
  getVolenteer(){
    return this.http.get<Volunteer[]>(this.servidor+"/"+this.recurso);
  }
  // Para buscar en el registrar y pueda verificar el editar
  getVolunteer(id: number){
    return this.http.get<Volunteer>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }
  //Para añadir un nuevo Voluntario
  addVolunteer(volunteer : Volunteer){
    return this.http.post<Volunteer>(this.servidor+"/"+this.recurso, volunteer);
  }
  //Para elimianr
  deleteVolunteer(id:number){
    return this.http.delete<Volunteer>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }

  //Para el llamado del editar
  editVolunteer(volunteer: Volunteer){
    return this.http.put<Volunteer>(this.servidor+"/"+this.recurso+"/"+volunteer.id.toString(),volunteer);
  }

  // Llamada HTTP para obtener el progreso del voluntario
  getVolunteerPoints(volunteerId: number) {
    
  }



  

}
