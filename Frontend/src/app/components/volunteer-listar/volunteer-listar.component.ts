import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { Volunteer } from '../../models/volunteer';
import { VolunteerService } from '../../services/volunteer.service';

@Component({
  selector: 'app-volunteer-listar',
  templateUrl: './volunteer-listar.component.html',
  styleUrl: './volunteer-listar.component.css'
})
export class VolunteerListarComponent {
  cant_Volunteer: number=0;
  dsVolunteer = new MatTableDataSource<Volunteer>();
  displayedColumns: string[] = ['id', 'volunteerName', 'email', 'address', 'points', 'category', 'level', 'actions'];
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dsVolunteer.filter = filterValue.trim().toLowerCase();
  }
  constructor(private volunteerService: VolunteerService) {}

  ngOnInit(): void {
    this.loadVolunteer();
    this.dsVolunteer.paginator = this.paginator;

  }
  ngAfterViewInit(): void {
    this.dsVolunteer.paginator = this.paginator!;
  }

  loadVolunteer(){
    this.volunteerService.getVolenteer().subscribe({
      next:(data:Volunteer[]) =>{
        this.dsVolunteer.data = data;
        this.cant_Volunteer = data.length;
      },
      error:(err) =>{
        console.log(err);
      }
    });
  }

  borrarVoluntario(id: number){
    this.volunteerService.deleteVolunteer(id).subscribe({
      next: (data)=>{
        this.loadVolunteer();
      },
      error:(err) =>{
        console.log(err);
      }
    })
  }

}
