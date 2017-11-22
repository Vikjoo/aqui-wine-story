package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.data.entity.PricingStrategy;
import net.spark.backend.data.entity.Product;

public interface PaymentConditionRepository extends JpaRepository<PaymentCondition, Long> {

	Page<PaymentCondition> findByNameLikeIgnoreCase(String nameFilter, Pageable pageable);

	int countByNameLikeIgnoreCase(String nameFilter);
	Page<PaymentCondition> findBy(Pageable page);
}
