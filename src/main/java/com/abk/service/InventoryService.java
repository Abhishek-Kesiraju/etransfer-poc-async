package com.abk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryService {
    //Check The Products Customer has enrolledFor
    public boolean checkProductAvailability(int connectId) {
        return true;
    }
}
