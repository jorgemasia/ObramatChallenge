package com.obramat.pedidos.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.obramat.pedidos.application.usecases.*;
import com.obramat.pedidos.domain.ports.*;


@Configuration
public class AppConfig {
    @Bean public CreateOrderUseCase createOrderUseCase(OrderRepository orderRepo){ return new CreateOrderUseCase(orderRepo); }
    @Bean public ListOrdersUseCase listOrdersUseCase(OrderRepository orderRepo){ return new ListOrdersUseCase(orderRepo); }
    // @Bean public ListProductsUseCase listProductsUseCase(ProductRepository repo){ return new ListProductsUseCase(repo); }
    @Bean public SearchProductsUseCase searchProductsUseCase(ProductRepository repo){ return new SearchProductsUseCase(repo); }
    @Bean public CreateProductUseCase createProductsUseCase(ProductRepository repo){ return new CreateProductUseCase(repo); }
    //@Bean public ListDetailsByOrderUseCase listDetalleUseCase(OrderDetailRepository repo){ return new ListDetailsByOrderUseCase(repo); }
    @Bean public DeleteOrderUseCase deleteOrderUseCase(OrderRepository orderRepo) {return new DeleteOrderUseCase(orderRepo);}
    @Bean public GetOrderUseCase getOrderUseCase(OrderRepository orderRepo) {return new GetOrderUseCase(orderRepo);}
}