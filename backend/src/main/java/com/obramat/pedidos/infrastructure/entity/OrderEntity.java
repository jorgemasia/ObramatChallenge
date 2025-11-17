package com.obramat.pedidos.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private LocalDate createdAt;
    private int totalWithoutTax;
    private int totalWithTax;
    private String status;    
    //@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetailEntity> details = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt; }
    public int getTotalWithoutTax() { return totalWithoutTax; }
    public void setTotalWithoutTax(int totalWithoutTax) { this.totalWithoutTax = totalWithoutTax; }
    public int getTotalWithTax() { return totalWithTax; }
    public void setTotalWithTax(int totalWithTax) { this.totalWithTax = totalWithTax; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrderDetailEntity> getDetails() { return details; }
    public void setDetails(List<OrderDetailEntity> details) { this.details = details; }

    public void addDetail(OrderDetailEntity detail) {
        details.add(detail);
        // detail.setOrder(this);
    }
}
