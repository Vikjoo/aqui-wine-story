package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.data.entity.PricingStrategy;

public interface PricingStrategyRepository extends JpaRepository<PricingStrategy, Long> {

	Page<PricingStrategy> findByNameLikeIgnoreCase(String nameFilter, Pageable pageable);

	int countByNameLikeIgnoreCase(String nameFilter);
}
