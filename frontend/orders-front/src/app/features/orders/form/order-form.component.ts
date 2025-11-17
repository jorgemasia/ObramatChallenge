import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OrdersService } from '../../../core/services/orders.service';
import { ProductsService } from '../../../core/services/products.service';
import { Observable, of } from 'rxjs';
import { debounceTime, switchMap, map } from 'rxjs/operators';
import { Order, OrderDetail, Product, OrderStatus } from '../../../core/models/order.model';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatTableDataSource } from '@angular/material/table';
import { RouterModule } from '@angular/router';
import { HttpService } from '../../../core/services/http.service';
import { MatIconModule } from '@angular/material/icon';
import { MatAutocompleteModule } from '@angular/material/autocomplete';

@Component({
  selector: 'app-order-form',
  templateUrl: './order-form.component.html',
  styleUrls: ['./order-form.component.scss'],
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
    MatIconModule,
    MatAutocompleteModule
  ]
})
export class OrderFormComponent implements OnInit {
  orderForm: FormGroup;
  orderId: number | null = null;
  statuses: OrderStatus[] = ['pending', 'cancelled', 'paid'];

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private orderService: OrdersService,
    private productsService : ProductsService
  ) {
    this.orderForm = this.fb.group({
      id: [{ value: '', disabled: true }],
      createdAt: [{ value: '', disabled: true }],
      totalWithoutTax: [0, Validators.required],
      totalWithTax: [0, Validators.required],
      status: ['pending', Validators.required],
      details: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.orderId = Number(this.route.snapshot.paramMap.get('id'));
    if (this.orderId) {
      this.orderService.get(String(this.orderId)).subscribe(order => this.setOrder(order));
    } else {
      this.addDetail();
    }
  }

  get details(): FormArray {
    return this.orderForm.get('details') as FormArray;
  }

  addDetail() {
    const detailGroup = this.fb.group({
      quantity: [1, [Validators.required, Validators.min(1)]],
      product:  [null, Validators.required],
      productSearch: [''],
      productOptions: [<Product[]>[]]
    });

    detailGroup.get('productSearch')?.valueChanges.pipe(
      debounceTime(300),
      switchMap(term => this.searchProducts(term ?? ''))
    ).subscribe(results => {
      detailGroup.patchValue({ productOptions: results }, { emitEvent: false });
    });

    this.details.push(detailGroup);
  }

  removeDetail(index: number) {
    this.details.removeAt(index);
    this.updateTotals();
  }

  searchProducts(term: string): Observable<Product[]> {
    if (!term || term.length < 3) return of([]);
    return this.productsService.search(term).pipe(map(products => products || []));
  }

  selectProduct(index: number, product: Product) {
    const detailGroup = this.details.at(index);
    detailGroup.patchValue({ product });
    this.updateTotals();
  }

  updateTotals() {
    const totalWithoutTax = this.details.controls.reduce((sum, ctrl) => {
      const detail = ctrl.value as OrderDetail;
      return sum + (detail.product?.price || 0) * (detail.quantity || 0);
    }, 0);

    const totalWithTax = totalWithoutTax * 1.09; // example 9% tax

    this.orderForm.patchValue({ totalWithoutTax, totalWithTax }, { emitEvent: false });
  }

  setOrder(order: Order) {
    this.orderForm.patchValue({
      id: order.id,
      createdAt: order.createdAt,
      totalWithoutTax: order.totalWithoutTax,
      totalWithTax: order.totalWithTax,
      status: order.status
    });

    this.details.clear();
    order.products?.forEach(d => {
      const group = this.fb.group({
        quantity: [d.quantity, [Validators.required, Validators.min(1)]],
        product: [d.product, Validators.required],
        productSearch: ['']
      });
      this.details.push(group);
    });
  }

  submit() {
    if (this.orderForm.invalid) return;

    const payload = {
      totalWithoutTax: this.orderForm.get('totalWithoutTax')?.value,
      totalWithTax: this.orderForm.get('totalWithTax')?.value,
      status: this.orderForm.get('status')?.value,
      details: this.details.controls.map(ctrl => ({
        quantity: ctrl.value.quantity,
        product: { id: ctrl.value.product.id }
      }))
    };

    if (this.orderId) {
      this.orderService.update(this.orderId, payload).subscribe(() => {
        this.router.navigate(['/list']);
      });
    } else {
      this.orderService.create(payload).subscribe(() => {
        this.router.navigate(['/list']);
      });
    }
  }
}
