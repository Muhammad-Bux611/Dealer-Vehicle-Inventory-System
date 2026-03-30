package com.inventory.dealer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.common.SubscriptionType;

public interface DealerRepository  extends JpaRepository<Dealer, UUID>{

	Page<Dealer> findByTenantId(String tenantId, Pageable pageable);

	Optional<Dealer> findByIdAndTenantId(UUID id, String tenantId);

	long countBySubscriptionType(SubscriptionType subscriptionType);
}
