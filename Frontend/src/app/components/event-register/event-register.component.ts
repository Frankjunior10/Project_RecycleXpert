import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Evento } from '../../models/evento';
import { EventType } from '../../models/eventType';
import { EventTypeService } from '../../services/event-type.service';
import { DatePipe } from '@angular/common';
import { OrganizationService } from '../../services/organization.service';
import { Organization } from '../../models/organization';

@Component({
  selector: 'app-event-register',
  templateUrl: './event-register.component.html',
  styleUrl: './event-register.component.css'
})
export class EventRegisterComponent {
  addFormEvent! : FormGroup;

  date!:String;
  eventType!: EventType[];
  organization! :Organization[];
  eventId:number=0;

  constructor(
    //services
    private eventService : EventService,
    private eventTypeService: EventTypeService,
    private organizationService: OrganizationService,
    //----------------------
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router,
    private activatedRoute:ActivatedRoute,
    private datePipe: DatePipe  // Inyecta DatePipe
  ){}

  ngOnInit(): void {
    this.CrearFormulario();
    this.cargarTiposDeEventos();
    this.cargarOrganizaciones(); 

    this.eventId = parseInt(this.activatedRoute.snapshot.params['id'], 10);
    if (this.eventId) {
      this.cargarEventoParaEdicion(this.eventId);
    }

  }
  cargarEventoParaEdicion(id: number): void {
    this.eventService.getEvent(id).subscribe({
      next: (dataEvent: Evento) => {
        this.addFormEvent.patchValue(dataEvent);
      },
      error: (err) => {
        console.error('Error al cargar datos del evento', err);
        this.snackBar.open('Error al cargar datos del evento', 'Ok', { duration: 3000 });
      }
    });
  }
  //Cargamos Eveentosss----------
  cargarTiposDeEventos(): void {
    this.eventTypeService.getEventTypes().subscribe({
      next:(data: EventType[]) => {
        this.eventType = data;
      },
      error: (err) => {
        console.error("Error al cargar tipos de eventos", err);
        this.snackBar.open("Error al cargar tipos de eventos", "Ok", { duration: 3000 });
      }
    });
  }
  //Cargamos Organizacionesssss-----
  cargarOrganizaciones(): void {
    this.organizationService.getOrganizations().subscribe({
      next: (dataOrganizations: Organization[]) => {
        this.organization = dataOrganizations;
      },
      error: (err) => {
        console.error("Error al cargar organizaciones", err);
        this.snackBar.open("Error al cargar organizaciones", "Ok", { duration: 3000 });
      }
    });
  }

  CrearFormulario(){
    this.addFormEvent = this.fb.group(
      {
        id:[""],
        eventName: ['', Validators.required],
        description: ['', Validators.required],
        date: ['', Validators.required],
        location: ['', Validators.required],
        capacity: [1, [Validators.required, Validators.min(1)]],
        organizationId: [''],
        eventTypeId: ['']
      }
    );

    this.eventId = parseInt(this.activatedRoute.snapshot.params["id"]);

    if (this.eventId>0 && this.eventId != undefined) {
      // Cargar los datos para edición
      this.eventService.getEvent(this.eventId).subscribe({
        next: (dataEvent: Evento) => {
          // Asignar valores al formulario
          this.addFormEvent.get("id")?.setValue(dataEvent.id);
          this.addFormEvent.get("eventName")?.setValue(dataEvent.eventName);
          this.addFormEvent.get("description")?.setValue(dataEvent.description);
          this.addFormEvent.get("date")?.setValue(dataEvent.date+"T00:00:00");
          this.addFormEvent.get("location")?.setValue(dataEvent.location);
          this.addFormEvent.get("capacity")?.setValue(dataEvent.capacity);

          this.eventTypeService.getEventType(dataEvent.eventTypeId).subscribe({
            next: (dataEventType: EventType) => {
              this.eventType = [dataEventType]; // asigna el dato como un array de un solo elemento si es necesario
            }
          });
          this.organizationService.getOrganization(dataEvent.organizationId).subscribe({
            next: (dataOrganization: Organization) =>{
              this.organization=[dataOrganization];
            }
          })
        },
        error:(err)=>{
          console.log(err);
        }
      })

    } else{
      //Cuando deseamos insertar
      this.eventId = 0;
    }

  }

  RegistrarEvento(){
    if (this.addFormEvent.invalid) {
      this.snackBar.open("Por favor, complete todos los campos correctamente.", "Ok", { duration: 3000 });
      return;
    }
   
    const evento: Evento = {
      id: this.eventId,
      eventName: this.addFormEvent.get("eventName")?.value,
      description: this.addFormEvent.get("description")?.value,
      date: this.addFormEvent.get("date")?.value,
      location: this.addFormEvent.get("location")?.value,
      capacity: this.addFormEvent.get('capacity')?.value,
      organizationId: this.addFormEvent.get('organizationId')?.value,
      eventTypeId: this.addFormEvent.get('eventTypeId')?.value // aseguramos que sea un número
    };
  
    console.log("Evento a registrar:", evento);

    if(this.eventId>0 && this.eventId!=undefined){
      this.eventService.editEvent(evento).subscribe({
        next: (data) => {
          this.router.navigate(['/event-listar']);
          this.snackBar.open("Evento Actualiza correctamente", "Ok", { duration: 3000 });
        },
        error: (err) => {
          console.error("Detalles del error:", err);  // Muestra el error completo en consola
          this.snackBar.open("Error al Actualizar el evento", "Ok", { duration: 3000 });
        }
      })
    }else{
      this.eventService.addEvento(evento).subscribe({
        next: (data) => {
          this.router.navigate(['/home']);
          this.snackBar.open("Evento registrado correctamente", "Ok", { duration: 3000 });

        },
        error: (err) => {
          console.error("Detalles del error:", err);  // Muestra el error completo en consola
          this.snackBar.open("Error al registrar el evento", "Ok", { duration: 3000 });
        }
      })

    }
  
  }
  onCancel(): void {
    this.router.navigate(['/home']);
  }

}
