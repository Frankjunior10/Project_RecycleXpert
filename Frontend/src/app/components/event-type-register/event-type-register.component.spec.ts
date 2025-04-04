import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventTypeRegisterComponent } from './event-type-register.component';

describe('EventTypeRegisterComponent', () => {
  let component: EventTypeRegisterComponent;
  let fixture: ComponentFixture<EventTypeRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EventTypeRegisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventTypeRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
