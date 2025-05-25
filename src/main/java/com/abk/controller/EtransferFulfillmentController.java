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


    @Autowired
    private EtransferFulfillmentService service;

    @PostMapping
    public ResponseEntity<Transaction> processOrder(@RequestBody Transaction transaction) throws InterruptedException {
        service.processOrder(transaction); // synchronous
        // asynchronous
        service.notifyCustomer(transaction);
        service.assignVendor(transaction);
        service.packaging(transaction);
        service.assignDeliveryPartner(transaction);
        service.assignTrailerAndDispatch(transaction);
        return ResponseEntity.ok(transaction);
    }
}
