import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReceitasDisplayComponent } from './receitas-display.component';

describe('ReceitasDisplayComponent', () => {
  let component: ReceitasDisplayComponent;
  let fixture: ComponentFixture<ReceitasDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReceitasDisplayComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReceitasDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
