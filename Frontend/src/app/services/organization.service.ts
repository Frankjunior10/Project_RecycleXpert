import { Injectable } from '@angular/core';
import { Organization } from '../models/organization';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {
    // Ruta a donde se conectará
    servidor: string = "http://localhost:8080/api";
    recurso: string = "organizations";

  constructor(private http: HttpClient) {}

  getOrganizations(){
    return this.http.get<Organization[]>(this.servidor+"/"+this.recurso);
  }

  getOrganization(id: number){
    return this.http.get<Organization>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }

  //Para eliminar
  deleteOrganization(id:number){
    return this.http.delete<Organization>(this.servidor+"/"+this.recurso+"/"+id.toString());
  }

  //Crear o insertar una nueva organización
  addOrganization(organization : Organization){
    return this.http.post<Organization>(this.servidor+"/"+this.recurso, organization);
  }

  //Para el llamado del editar
  editEvent(organization : Organization){
    return this.http.put<Organization>(this.servidor+"/"+this.recurso+"/"+organization.id.toString(),organization);
  }


}
