import { TestBed } from '@angular/core/testing';
import { OrdersListComponent } from './orders-list.component';

describe('OrdersListComponent', () => {
  it('should create', () => {
    const fixture = TestBed.createComponent(OrdersListComponent);
    const comp = fixture.componentInstance;
    expect(comp).toBeTruthy();
  });
});
