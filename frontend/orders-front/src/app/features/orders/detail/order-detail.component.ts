import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order } from '../../../core/models/order.model';
import { HttpService } from '../../../core/services/http.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss'],
  standalone : true
})
export class OrderDetailComponent implements OnInit {
  order$: Observable<Order>;
  orderId: string;

  constructor(
    private route: ActivatedRoute,
    private http: HttpService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.paramMap.get('id')!;
    this.order$ = this.http.get<Order>(`orders/${this.orderId}`);
  }

  editOrder() {
    this.router.navigate(['/orders', this.orderId, 'edit']);
  }
}
