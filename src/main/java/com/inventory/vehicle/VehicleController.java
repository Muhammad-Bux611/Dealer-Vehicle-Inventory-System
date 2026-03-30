package com.inventory.vehicle;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.common.SubscriptionType;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle) {
        return vehicleService.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable UUID id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping
    public Page<Vehicle> getAll(
            @RequestParam(required=false) String model,
            @RequestParam(required=false) VehicleStatus status,
            @RequestParam(required=false) BigDecimal priceMin,
            @RequestParam(required=false) BigDecimal priceMax,
            @RequestParam(required=false) SubscriptionType subscription,
            Pageable pageable
    ) {
        return vehicleService.getVehicles(model, status, priceMin, priceMax, subscription, pageable);
    }

    @PatchMapping("/{id}")
    public Vehicle update(@PathVariable UUID id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        vehicleService.deleteVehicle(id);
    }
}