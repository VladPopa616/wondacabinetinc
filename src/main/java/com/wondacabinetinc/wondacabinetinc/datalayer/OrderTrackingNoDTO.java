package com.wondacabinetinc.wondacabinetinc.datalayer;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrackingNoDTO {
    private String trackingNo;
    private String orderStatus;
}
