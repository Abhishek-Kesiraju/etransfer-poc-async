package com.abk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    private int connectId;
    private String name;
    private String productType; // email, commerceTransfer, Account based etc
    private int qty;
    private double price;
    private String trackingId;
}
