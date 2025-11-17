package com.obramat.pedidos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "order_details")
@AllArgsConstructor
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @ManyToOne
    // @JoinColumn(name = "order_id")
    // private OrderEntity order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    private int quantity;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(ProductEntity product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    // public OrderEntity getOrder() {
    //     return order;
    // }

    public ProductEntity getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // public void setOrder(OrderEntity order) {
    //     this.order = order;
    // }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}