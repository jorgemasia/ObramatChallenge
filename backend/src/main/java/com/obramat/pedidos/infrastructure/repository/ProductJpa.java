package com.obramat.pedidos.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.obramat.pedidos.infrastructure.entity.ProductEntity;

@Repository
public interface ProductJpa extends JpaRepository<ProductEntity, Long> {}