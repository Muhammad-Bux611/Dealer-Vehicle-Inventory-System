package com.inventory.vehicle;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.inventory.common.SubscriptionType;
import com.inventory.dealer.Dealer;

import jakarta.persistence.criteria.Join;

public class VehicleSpecification {

	
	public static Specification<Vehicle> hasTenant(String tenantId) {
	    return (root, query, cb) ->
	            cb.equal(root.get("tenantId"), tenantId);
	}
	
	
	public static Specification<Vehicle> hasModel(String model) {
	    return (root, query, cb) ->
	            cb.like(cb.lower(root.get("model")), "%" + model.toLowerCase() + "%");
	}
	public static Specification<Vehicle> hasStatus(VehicleStatus status) {
	    return (root, query, cb) ->
	            cb.equal(root.get("status"), status);
	}
	
	public static Specification<Vehicle> priceBetween(BigDecimal min, BigDecimal max) {
	    return (root, query, cb) -> {
	        if (min != null && max != null) {
	            return cb.between(root.get("price"), min, max);
	        } else if (min != null) {
	            return cb.greaterThanOrEqualTo(root.get("price"), min);
	        } else if (max != null) {
	            return cb.lessThanOrEqualTo(root.get("price"), max);
	        }
	        return null;
	    };
	}
	
	
	public static Specification<Vehicle> hasDealerSubscription(SubscriptionType subscription) {
	    return (root, query, cb) -> {
	        Join<Vehicle, Dealer> dealerJoin = root.join("dealer");
	        return cb.equal(dealerJoin.get("subscriptionType"), subscription);
	    };
	}
	
	
}
