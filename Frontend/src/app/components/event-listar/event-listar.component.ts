import { Component, ViewChild, OnInit, AfterViewInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Evento } from '../../models/evento'; 
import { EventService } from '../../services/event.service'; 

@Component({
  selector: 'app-event-listar',
  templateUrl: './event-listar.component.html',
  styleUrls: ['./event-listar.component.css']
})
export class EventListarComponent implements OnInit, AfterViewInit {
confirmarEliminar(arg0: any) {
throw new Error('Method not implemented.');
}
  cant_Event: number = 0;
  dsEvent = new MatTableDataSource<Evento>();
  displayedColumns: string[] = ['id', 'eventName', 'description', 'date', 'location', 'capacity', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private eventService: EventService) {}

  ngOnInit(): void {
    this.loadEvents();
  }

  ngAfterViewInit(): void {
    this.dsEvent.paginator = this.paginator;
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dsEvent.filter = filterValue.trim().toLowerCase();
  }

  loadEvents(): void {
    const userId = this.getLoggedUserId(); 

    this.eventService.getEvents(userId).subscribe({
      next: (data: Evento[]) => {
        this.dsEvent.data = data;
        this.cant_Event = data.length;
      },
      error: (err) => {
        console.error('Error al cargar eventos:', err);
      }
    });
  }

  borrarEvento(id: number): void {
    this.eventService.deleteEvent(id).subscribe({
      next: () => {
        this.loadEvents(); 
      },
      error: (err) => {
        console.error('Error al eliminar evento:', err);
      }
    });
  }

  // Método para obtener el ID del usuario logeado
  private getLoggedUserId(): number {
    const user = JSON.parse(localStorage.getItem('loggedUser') || '{}');
    return user?.id || 0; 
  }
}
