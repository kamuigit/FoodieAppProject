import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReciptPageComponent } from './recipt-page.component';

describe('ReciptPageComponent', () => {
  let component: ReciptPageComponent;
  let fixture: ComponentFixture<ReciptPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReciptPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReciptPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
