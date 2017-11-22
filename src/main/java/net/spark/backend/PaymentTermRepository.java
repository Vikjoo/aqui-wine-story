package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PaymentTerm;
import net.spark.backend.data.entity.PickupLocation;

public interface PaymentTermRepository extends JpaRepository<PaymentTerm, Long> {

	Page<PaymentTerm> findByNameLikeIgnoreCase(String nameFilter, Pageable pageable);

	int countByNameLikeIgnoreCase(String nameFilter);
}
