package com.inventory.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.common.SubscriptionType;
import com.inventory.dealer.DealerRepository;

@Service
public class AdminService {
    @Autowired
    private DealerRepository dealerRepository;

    public Map<String, Long> countBySubscription() {
        Map<String, Long> result = new HashMap<>();
        result.put("BASIC", dealerRepository.countBySubscriptionType(SubscriptionType.BASIC));
        result.put("PREMIUM", dealerRepository.countBySubscriptionType(SubscriptionType.PREMIUM));
        return result;
    }
}
