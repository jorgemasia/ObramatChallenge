export type OrderStatus = 'pending' | 'cancelled' | 'paid';

export interface Order {
  id: number;
  createdAt: string;
  productCount: number;
  totalWithoutTax: number;
  totalWithTax: number;
  status: OrderStatus;
  products?: OrderDetail[];
}

export interface OrderDetail {
  quantity: number;
  product: Product;
}

export interface Product {
  name: string;
  quantity: number;
  price: number;
}

