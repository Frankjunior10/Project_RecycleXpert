import { Volunteer } from "./volunteer";

export interface User{
    id:number,
    userName:string,
    password:string,
    authorities:string,
    volunteer?: Volunteer; // Relación uno a uno con Volunteer, opcional si aún no se asigna

}
