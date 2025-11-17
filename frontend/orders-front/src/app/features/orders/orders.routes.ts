import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

export const ORDERS_ROUTES: Routes = [
//   { path: '', component: OrdersListComponent },
//   { path: 'create', component: OrderFormComponent },
//   { path: ':id/edit', component: OrderFormComponent },
//   { path: ':id', component: OrderDetailComponent },
  { path: 'list', loadComponent: () => import('./list/orders-list.component').then(m => m.OrdersListComponent) },
  { path: 'new', loadComponent: () => import('./form/order-form.component').then(m => m.OrderFormComponent) },
  { path: ':id', loadComponent: () => import('./detail/order-detail.component').then(m => m.OrderDetailComponent) }   
];

@NgModule({
  imports: [RouterModule.forChild(ORDERS_ROUTES)],
  exports: [RouterModule]
})
export class OrdersRoutingModule {}

