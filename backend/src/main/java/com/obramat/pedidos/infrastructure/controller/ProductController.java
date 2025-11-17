package com.obramat.pedidos.infrastructure.controller;

import com.obramat.pedidos.application.usecases.*;
import com.obramat.pedidos.domain.model.Product;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    // private final ListProductsUseCase listProducts;
    private final SearchProductsUseCase searchProducts;
    private final CreateProductUseCase createProduct;

    public ProductController(//ListProductsUseCase listProducts, 
                SearchProductsUseCase searchProducts
            , CreateProductUseCase createProduct
            ) {
        //this.listProducts = listProducts;
        this.searchProducts = searchProducts;
        this.createProduct = createProduct;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return createProduct.execute(product);
    }
    @GetMapping("/search")
    public List<Product> search(@RequestParam String nameLike) {
        return searchProducts.execute(nameLike);
    }
}