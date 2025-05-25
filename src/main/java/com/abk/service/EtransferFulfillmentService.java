package com.abk.service;
import com.abk.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EtransferFulfillmentService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    /* All Required process */
    /*
      1. Inventory service check Transaction type availability
      2. Process payment for order (Enrolment Check)
      3. Notify to the user
      3. Assign to vendor - Check Transfer Eligibility
      4. Fraud Check of transfer
      5. Account Posting Debit
      6. Interac Begin
      7. Interac Commit
      **/

    public Transaction processOrder(Transaction order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if (inventoryService.checkProductAvailability(order.getConnectId())) {
            log.info("Enrolment Check completed - Synchronous");
            //handle exception here
            paymentService.processPayment(order);
        } else {
            throw new RuntimeException("Technical issue please retry");
        }
        return order;
    }

    @Async("asyncTaskExecutor")
    public void notifyCustomer(Transaction order) throws InterruptedException {
        Thread.sleep(4000L);
        log.info("Notified to the Customer " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void eligbilityCheck(Transaction order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info("Transfer Eligibility check completed" + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void fraudCheck(Transaction order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Transfer Fraud Check completed " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void accountPosting(Transaction order) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("Transfer Account Posting completed " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void interacBegin(Transaction order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Interac Begin completed " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void interacCommit(Transaction order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Interac Commit completed " + Thread.currentThread().getName());
    }

}
