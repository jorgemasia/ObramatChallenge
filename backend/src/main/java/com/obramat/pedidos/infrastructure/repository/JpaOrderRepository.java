package com.obramat.pedidos.infrastructure.repository;

import com.obramat.pedidos.domain.model.Order;
import com.obramat.pedidos.domain.ports.OrderRepository;
import com.obramat.pedidos.infrastructure.entity.OrderEntity;
import com.obramat.pedidos.infrastructure.entity.OrderMapper;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaOrderRepository implements OrderRepository {
    private final OrderJpa jpa;
    private final OrderMapper mapper;

    public JpaOrderRepository(OrderJpa jpa, OrderMapper mapper){
        this.jpa = jpa;
        this.mapper = mapper;
    }

    @Override
    public List<Order> findAll() {
        return jpa.findAll().stream()
                .map(e -> mapper.toDomain(e))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpa.findById(id).map(e -> mapper.toDomain(e));
    }

    @Override
    public void save(Order order) {
        OrderEntity entity = mapper.toEntity(order);        
        jpa.save(entity);
    }

    @Override
    public void delete(Long id) {
        jpa.deleteById(id);
    }
}