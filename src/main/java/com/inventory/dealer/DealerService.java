package com.inventory.dealer;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inventory.common.CustomException;

@Service
public class DealerService {
    @Autowired
    private DealerRepository dealerRepository;

    @Autowired
    private ModelMapper mapper;
    
    public Dealer createDealer(Dealer dealer){
    	
        dealer.setTenantId(dealer.getTenantId());
        
        return dealerRepository.save(dealer);
        
    }

    public Dealer getDealer(UUID id) {
        return dealerRepository.findByIdAndTenantId(id, TenantContext.getTenant())
                .orElseThrow(CustomException.CrossTenantAccessException::new);
    }

    public Page<Dealer> getAllDealers(Pageable pageable) {
        return dealerRepository.findByTenantId(TenantContext.getTenant(), pageable);
    }

    public Dealer updateDealer(UUID id, Dealer updated) {
        Dealer dealer = getDealer(id);
        dealer.setName(updated.getName());
        dealer.setEmail(updated.getEmail());
        dealer.setSubscriptionType(updated.getSubscriptionType());
        return dealerRepository.save(dealer);
    }

    public void deleteDealer(UUID id) {
        Dealer dealer = getDealer(id);
        dealerRepository.delete(dealer);
    }
}
