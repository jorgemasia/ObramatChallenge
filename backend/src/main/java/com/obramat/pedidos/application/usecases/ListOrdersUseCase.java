package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Order;
import com.obramat.pedidos.domain.ports.OrderRepository;
import java.util.List;

public class ListOrdersUseCase {
    private final OrderRepository repo;

    public ListOrdersUseCase(OrderRepository repo) {
        this.repo = repo;
    }

    public List<Order> execute() {
        return repo.findAll();
    }
}