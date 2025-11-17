import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource } from '@angular/material/table';
import { Observable, tap, map } from 'rxjs';
import { RouterModule } from '@angular/router';
import { Order } from '../../../core/models/order.model';
import { HttpService } from '../../../core/services/http.service';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class OrdersListComponent implements OnInit {
  // Observable principal (puedes usarlo en template si lo prefieres)
  orders$: Observable<Order[]>;

  // MatTableDataSource para paginación/ordenación local
  dataSource = new MatTableDataSource<Order>([]);

  // Columnas (mantengo el nombre que ya tenías)
  displayedColumns: string[] = [
    'id',
    'createdAt',
    'productCount',
    'totalWithoutTax',
    'totalWithTax',
    'status',
    'actions'
  ];

  // filtros (reactive)
  statusFilter = new FormControl('');
  priceFilter = new FormControl('');
  dateFilter = new FormControl('');

  
  private originalOrders: Order[] = [];

  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
    
    this.orders$ = this.httpService.get<Order[]>('orders').pipe(
      map(list => list || []),
      tap(list => {
        this.originalOrders = list.slice(); 
        this.dataSource.data = list;
      })
    );

    this.orders$.subscribe();
    
    this.setupFilters();
  }

  private setupFilters(): void {
    this.statusFilter.valueChanges.subscribe(() => this.applyFilters());
    this.priceFilter.valueChanges.subscribe(() => this.applyFilters());
    this.dateFilter.valueChanges.subscribe(() => this.applyFilters());
  }

  private applyFilters(): void {
    let filtered = this.originalOrders.slice();

    const status = (this.statusFilter.value || '').toString().trim().toLowerCase();
    const priceRaw = this.priceFilter.value;
    const price = priceRaw === null || priceRaw === '' ? NaN : Number(priceRaw);
    const date = this.dateFilter.value;

    if (status) {
      filtered = filtered.filter(o => (o.status || '').toString().toLowerCase().includes(status));
    }

    if (!isNaN(price)) {
      filtered = filtered.filter(o => (o.totalWithTax ?? 0) >= price);
    }

    if (date) {
      const target = new Date(date).toISOString().slice(0, 10);
      filtered = filtered.filter(o => new Date(o.createdAt).toISOString().slice(0, 10) === target);
    }

    this.dataSource.data = filtered;
  }

  // acciones
  viewOrder(id: string | number) {

  }

  editOrder(id: string | number) {
  }

  deleteOrder(id: string | number) {
    if (!confirm('Are you sure you want to delete this order?')) return;
    this.httpService.delete('orders',String(id)).subscribe(() => {
      
      this.originalOrders = this.originalOrders.filter(o => String(o.id) !== String(id));
      this.applyFilters();
    });
  }
}
