package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Order;
import com.obramat.pedidos.domain.ports.OrderRepository;
import java.time.LocalDate;

public class CreateOrderUseCase {
    private final OrderRepository repo;

    public CreateOrderUseCase(OrderRepository repo) {
        this.repo = repo;
    }

    public Order execute(Order order) {        
        repo.save(order);
        return order;
    }
}