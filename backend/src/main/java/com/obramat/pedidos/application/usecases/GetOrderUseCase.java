package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Order;
import com.obramat.pedidos.domain.ports.OrderRepository;
import java.util.Optional;

public class GetOrderUseCase {
    private final OrderRepository repo;

    public GetOrderUseCase(OrderRepository repo) {
        this.repo = repo;
    }

    public Optional<Order> execute(Long id) {
        return repo.findById(id);
    }
}