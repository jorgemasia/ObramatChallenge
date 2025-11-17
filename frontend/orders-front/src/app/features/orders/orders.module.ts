// src/app/orders/orders.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router'; 
import { MaterialModule } from '../../material/material.module';
import { OrdersRoutingModule } from './orders.routes';

import { OrdersListComponent } from './list/orders-list.component';
import { OrderFormComponent } from './form/order-form.component';
import { OrderDetailComponent } from './detail/order-detail.component';

@NgModule({
//   declarations: [
//     OrdersListComponent,
//     OrderFormComponent,
//     OrderDetailComponent,
//   ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MaterialModule,
    OrdersRoutingModule,
    RouterModule
  ]
})
export class OrdersModule {}