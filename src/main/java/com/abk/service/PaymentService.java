package com.abk.service;

import com.abk.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public void processPayment(Transaction order) throws InterruptedException {
        log.info("Initiate e-transfer payment order " + order.getConnectId());
        //call actual payment gateway
        Thread.sleep(2000L);
        log.info("We sent your e-transfer payment order " + order.getConnectId());
    }
}
