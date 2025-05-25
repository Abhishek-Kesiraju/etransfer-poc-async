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
      2. Process payment for order
      3. Notify to the user
      3. Assign to vendor - Check Transfer Eligibility
      4. packaging - Fraud Check of transfer
      5. assign delivery partner - Account Posting Debit
      6. assign trailer - Interac Begin
      7. dispatch product - Interac Commit
      **/

    public Transaction processOrder(Transaction order) throws InterruptedException {
        order.setTrackingId(UUID.randomUUID().toString());
        if (inventoryService.checkProductAvailability(order.getConnectId())) {
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
    public void assignVendor(Transaction order) throws InterruptedException {
        Thread.sleep(5000L);
        log.info(" Transfer Eligibility check completed" + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void packaging(Transaction order) throws InterruptedException {
        Thread.sleep(2000L);
        log.info("Transfer Fraud Check completed " + Thread.currentThread().getName());
    }
    @Async("asyncTaskExecutor")
    public void assignDeliveryPartner(Transaction order) throws InterruptedException {
        Thread.sleep(10000L);
        log.info("Transfer Account Posting completed " + Thread.currentThread().getName());
    }

    @Async("asyncTaskExecutor")
    public void assignTrailerAndDispatch(Transaction order) throws InterruptedException {
        Thread.sleep(3000L);
        log.info("Interac Begin completed \n Interac Commit complete" + Thread.currentThread().getName());
    }
}
