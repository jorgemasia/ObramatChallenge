package com.obramat.pedidos.domain.ports;

import com.obramat.pedidos.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);
}