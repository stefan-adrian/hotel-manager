import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlimentCreationComponent } from './aliment-creation.component';

describe('AlimentCreationComponent', () => {
  let component: AlimentCreationComponent;
  let fixture: ComponentFixture<AlimentCreationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlimentCreationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlimentCreationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
