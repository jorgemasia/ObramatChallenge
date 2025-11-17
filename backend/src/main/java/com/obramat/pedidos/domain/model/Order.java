package com.obramat.pedidos.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.obramat.pedidos.infrastructure.entity.OrderDetailEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder.Default;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Order {
    private Long id;
    
    private LocalDate createdAt;
    private int productCount;
    private int totalWithoutTax;
    private int totalWithTax;
    private String status;    
    private List<OrderDetail> details = new ArrayList<>();

    public Order(){

    }

    public List<OrderDetail> getDetails() { return details; }
    public void setDetails(List<OrderDetail> details) { this.details = details; }

    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public int getProductCount() {    return details.stream()
                  .mapToInt(d -> d.getQuantity()) 
                  .sum(); }
    
    public void setProductCount(int productCount) { this.productCount = productCount; }

    public int getTotalWithoutTax() { return totalWithoutTax; }
    public void setTotalWithoutTax(int totalWithoutTax) { this.totalWithoutTax = totalWithoutTax; }
    public int getTotalWithTax() { return totalWithTax; }
    public void setTotalWithTax(int totalWithTax) { this.totalWithTax = totalWithTax; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }


    

    public void addDetail(OrderDetail detail) {
        details.add(detail);
    }
}