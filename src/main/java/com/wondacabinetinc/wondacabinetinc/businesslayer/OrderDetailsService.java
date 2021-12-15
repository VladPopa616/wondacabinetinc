package com.wondacabinetinc.wondacabinetinc.businesslayer;

import com.wondacabinetinc.wondacabinetinc.datalayer.OrderDetails;

public interface OrderDetailsService {
    public OrderDetails addOrderDetailsToOrder(OrderDetails orderDetails, Integer orderId);
}
