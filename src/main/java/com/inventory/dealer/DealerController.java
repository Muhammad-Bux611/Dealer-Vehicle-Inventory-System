package com.inventory.dealer;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dealers")
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @PostMapping
    public Dealer create(@RequestBody Dealer dealer) {
        return dealerService.createDealer(dealer);
    }

    @GetMapping("/{id}")
    public Dealer get(@PathVariable UUID id) {
        return dealerService.getDealer(id);
    }

    @GetMapping
    public Page<Dealer> getAll(Pageable pageable) {
        return dealerService.getAllDealers(pageable);
    }

    @PatchMapping("/{id}")
    public Dealer update(@PathVariable UUID id, @RequestBody Dealer dealer) {
        return dealerService.updateDealer(id, dealer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        dealerService.deleteDealer(id);
    }
}