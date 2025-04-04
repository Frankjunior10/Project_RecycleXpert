import { User } from "./user";

export interface Volunteer{
    id: number;
    volunteerName: string;
    email: string;
    address: string;
    points: number;
    category: String;
    level: number;
    //user: number; // Relación uno a uno, opcional si aún no se asigna
}
