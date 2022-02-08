package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.Order;
import com.wondacabinetinc.wondacabinetinc.datalayer.OrderTrackingNoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface OrderMapper {
    @Mapping(target = "trackingNo", expression = "java(entity.getTrackingNo())")
    OrderTrackingNoDTO entityToModel(Order entity);
}
