import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountRoomservicesComponent } from './account-roomservices.component';

describe('AccountRoomservicesComponent', () => {
  let component: AccountRoomservicesComponent;
  let fixture: ComponentFixture<AccountRoomservicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AccountRoomservicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AccountRoomservicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
