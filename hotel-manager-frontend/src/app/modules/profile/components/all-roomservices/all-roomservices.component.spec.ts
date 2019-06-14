import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRoomservicesComponent } from './all-roomservices.component';

describe('AllRoomservicesComponent', () => {
  let component: AllRoomservicesComponent;
  let fixture: ComponentFixture<AllRoomservicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllRoomservicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRoomservicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
