package com.inventory.vehicle;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.inventory.common.CustomException;
import com.inventory.common.SubscriptionType;
import com.inventory.dealer.TenantContext;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        vehicle.setTenantId(TenantContext.getTenant());
        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicle(UUID id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        if(!vehicle.getTenantId().equals(TenantContext.getTenant()))
            throw new CustomException.CrossTenantAccessException();
        return vehicle;
    }

    public Page<Vehicle> getVehicles(String model, VehicleStatus status, BigDecimal priceMin, BigDecimal priceMax, SubscriptionType subscription, Pageable pageable) {
        Specification<Vehicle> spec = Specification.where(VehicleSpecification.hasTenant(TenantContext.getTenant()));

        if(model != null) spec = spec.and(VehicleSpecification.hasModel(model));
        if(status != null) spec = spec.and(VehicleSpecification.hasStatus(status));
        if(priceMin != null || priceMax != null) spec = spec.and(VehicleSpecification.priceBetween(priceMin, priceMax));
        if(subscription != null) spec = spec.and(VehicleSpecification.hasDealerSubscription(subscription));

        return vehicleRepository.findAll(spec, pageable);
    }

    public Vehicle updateVehicle(UUID id, Vehicle updated) {
        Vehicle vehicle = getVehicle(id);
        vehicle.setModel(updated.getModel());
        vehicle.setPrice(updated.getPrice());
        vehicle.setStatus(updated.getStatus());
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(UUID id) {
        Vehicle vehicle = getVehicle(id);
        vehicleRepository.delete(vehicle);
    }
}