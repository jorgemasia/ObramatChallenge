package com.obramat.pedidos.infrastructure.controller;

import com.obramat.pedidos.application.usecases.DeleteOrderUseCase;
import com.obramat.pedidos.application.usecases.CreateOrderUseCase;
import com.obramat.pedidos.application.usecases.ListOrdersUseCase;
import com.obramat.pedidos.application.usecases.GetOrderUseCase;
import com.obramat.pedidos.domain.model.OrderDetail;
import com.obramat.pedidos.domain.model.Order;

import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final CreateOrderUseCase createOrder;
    private final ListOrdersUseCase listOrders;
    private final DeleteOrderUseCase deleteOrder;
    private final GetOrderUseCase getOrder;

    public OrderController(CreateOrderUseCase createOrder, ListOrdersUseCase listOrders, 
                DeleteOrderUseCase deleteOrder, GetOrderUseCase getOrder) 
                {
        this.createOrder = createOrder;
        this.listOrders = listOrders;
        this.deleteOrder = deleteOrder;
        this.getOrder = getOrder;
    }

    @GetMapping
    public List<Order> all() {
        return listOrders.execute();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return createOrder.execute(order);
    }

    @GetMapping("/{pedidoId}")
    public Optional<Order> lines(@PathVariable Long pedidoId) {
        return getOrder.execute(pedidoId);
    }

    @DeleteMapping("/{pedidoId}")
    public void delete(@PathVariable Long pedidoId) {
        deleteOrder.execute(pedidoId);
    }

    // @PostMapping("/{pedidoId}/lines")
    // public OrderDetail addLinea(@PathVariable Long pedidoId, @RequestBody AddLineaRequest req) {
    //     return addDetalle.execute(pedidoId, req.productoId, req.cantidad);
    // }

    public static class AddLineaRequest {
        public Long productoId;
        public int cantidad;
    }
}
