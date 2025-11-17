package com.obramat.pedidos.domain.ports;

import com.obramat.pedidos.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> findAll();
    Optional<Order> findById(Long id);
    void save(Order order);
    void delete(Long id);
}