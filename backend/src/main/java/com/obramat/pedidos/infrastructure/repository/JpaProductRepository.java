package com.obramat.pedidos.infrastructure.repository;

import com.obramat.pedidos.domain.model.Product;
import com.obramat.pedidos.domain.ports.ProductRepository;
import com.obramat.pedidos.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class JpaProductRepository implements ProductRepository {
private final ProductJpa jpa;
public JpaProductRepository(ProductJpa jpa){ this.jpa = jpa; }


@Override
public List<Product> findAll(){
return jpa.findAll().stream()
.map(e -> new Product(e.getId(), e.getName(), e.getPrice()))
.collect(Collectors.toList());
}


@Override
public Optional<Product> findById(Long id){
return jpa.findById(id).map(e -> new Product(e.getId(), e.getName(), e.getPrice()));
}


@Override
public void save(Product product){
ProductEntity entity = new ProductEntity(product.getName(), product.getPrice());
jpa.save(entity);
}
}

