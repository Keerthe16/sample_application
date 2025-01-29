import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AboutContactsComponent } from './about-contacts.component';

describe('AboutContactsComponent', () => {
  let component: AboutContactsComponent;
  let fixture: ComponentFixture<AboutContactsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AboutContactsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AboutContactsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
