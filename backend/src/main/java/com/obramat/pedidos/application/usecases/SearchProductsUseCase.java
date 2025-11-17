package com.obramat.pedidos.application.usecases;

import com.obramat.pedidos.domain.model.Product;
import com.obramat.pedidos.domain.ports.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProductsUseCase {
    private final ProductRepository repo;
    public SearchProductsUseCase(ProductRepository repo) { this.repo = repo; }

    public List<Product> execute(String nameLike) {
        if (nameLike == null || nameLike.length() < 3) throw new IllegalArgumentException("Se requieren al menos 3 caracteres");
        return repo.findAll()
                .stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(nameLike.toLowerCase()))
                .collect(Collectors.toList());
    }
}