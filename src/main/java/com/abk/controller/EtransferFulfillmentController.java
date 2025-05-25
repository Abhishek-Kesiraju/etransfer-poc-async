package com.abk.controller;
import com.abk.dto.Transaction;
import com.abk.service.EtransferFulfillmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendMoney")
public class EtransferFulfillmentController {
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

    @Autowired
    private EtransferFulfillmentService service;

    @PostMapping
    public ResponseEntity<Transaction> processOrder(@RequestBody Transaction transaction) throws InterruptedException {
        service.processOrder(transaction); // synchronous
        // asynchronous
        service.notifyCustomer(transaction);
        service.eligbilityCheck(transaction);
        service.fraudCheck(transaction);
        service.accountPosting(transaction);
        service.interacBegin(transaction);
        service.interacCommit(transaction);
        return ResponseEntity.ok(transaction);
    }
}
