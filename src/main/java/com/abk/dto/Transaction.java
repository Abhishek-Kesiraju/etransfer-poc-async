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
    private String channelName;
    private String transferType; // email, commerceTransfer, Account based etc
    private double price;
    private String trackingId; //traceId
}
