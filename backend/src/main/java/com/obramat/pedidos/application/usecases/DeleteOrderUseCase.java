package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Order;
import com.obramat.pedidos.domain.ports.OrderRepository;
import java.time.LocalDate;

public class DeleteOrderUseCase {
    private final OrderRepository repo;

    public DeleteOrderUseCase(OrderRepository repo) {
        this.repo = repo;
    }

    public void execute(Long id) {        
        repo.delete(id);
        return;
    }
}