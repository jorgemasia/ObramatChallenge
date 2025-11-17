import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

export const routes: Routes = [    {
    path: '',
    loadComponent: () =>
      import('./features/home/home.component').then(m => m.HomeComponent)
  },
//   {
//     path: 'orders',
//     loadChildren: () =>
//       import('./features/orders/orders.routes').then(m => m.ORDERS_ROUTES)    
//   },
  
//   { path: 'orders', loadChildren: () => import('./features/orders/orders.module').then(m => m.OrdersModule) },
  { path: 'list', loadComponent: () => import('./features/orders/list/orders-list.component').then(m => m.OrdersListComponent) },
  { path: 'new', loadComponent: () => import('./features/orders/form/order-form.component').then(m => m.OrderFormComponent) },
  { path: ':id', loadComponent: () => import('./features/orders/detail/order-detail.component').then(m => m.OrderDetailComponent) }  ,
    { path: '**', redirectTo: '' },

];


@NgModule({
  imports: [RouterModule, RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
