package com.obramat.pedidos.infrastructure.entity;

import com.obramat.pedidos.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    //@Mapping(target = "details", ignore = true)
    // @Mappings({
    //     @Mapping(source = "id", target = "id"),
    //     @Mapping(source = "totalWithTax", target = "totalWithTax"),
    //     @Mapping(source = "totalWithoutTax", target = "totalWithoutTax"),
    //     @Mapping(source = "details", target = "details"
    //     )
    // })     
    Order toDomain(OrderEntity entity);

    //@Mapping(target = "details", ignore = true)
    // @Mappings({
    //     @Mapping(source = "id", target = "id"),
    //     @Mapping(source = "totalWithTax", target = "totalWithTax"),
    //     @Mapping(source = "totalWithoutTax", target = "totalWithoutTax"),
    //     @Mapping(source = "details", target = "details"
    //     )
    // })      
    OrderEntity toEntity(Order order);
}
