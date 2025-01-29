import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientApproveComponent } from './client-approve.component';

describe('ClientApproveComponent', () => {
  let component: ClientApproveComponent;
  let fixture: ComponentFixture<ClientApproveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ClientApproveComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientApproveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
