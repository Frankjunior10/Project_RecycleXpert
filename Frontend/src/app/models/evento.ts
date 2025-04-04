export interface Evento{
    id: number;
    eventName: string;
    description: string;
    date: string;
    location: string;
    capacity: number;
    organizationId: number;  // Opcional: si deseas vincular a la organización
    eventTypeId: number;     // Opcional: si deseas vincular a un tipo de evento
}
