package com.obramat.pedidos.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obramat.pedidos.infrastructure.entity.OrderEntity;

@Repository
public interface OrderJpa extends JpaRepository<OrderEntity, Long> {}