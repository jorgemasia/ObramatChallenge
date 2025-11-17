package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Product;
import com.obramat.pedidos.domain.ports.ProductRepository;

public class CreateProductUseCase {
    private final ProductRepository repo;

    public CreateProductUseCase(ProductRepository repo) {
        this.repo = repo;
    }

    public Product execute(Product product) {        
        repo.save(product);
        return product;
    }
}