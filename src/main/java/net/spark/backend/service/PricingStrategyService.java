package net.spark.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.spark.app.BeanLocator;
import net.spark.backend.CurrencyRepository;
import net.spark.backend.PickupLocationRepository;
import net.spark.backend.PricingStrategyRepository;
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PickupLocation;
import net.spark.backend.data.entity.PricingStrategy;

@Service
public class PricingStrategyService {

	private PricingStrategyRepository pricingStrategyRepository;

	public Page<PricingStrategy> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getPricingStrategyRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getPricingStrategyRepository().findByNameLikeIgnoreCase("%", pageable);
		}
	}

	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getPricingStrategyRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getPricingStrategyRepository().countByNameLikeIgnoreCase("%");
		}
	}

	public PricingStrategy getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	protected PricingStrategyRepository getPricingStrategyRepository() {
		if (pricingStrategyRepository == null) {
			pricingStrategyRepository = BeanLocator.find(PricingStrategyRepository.class);
		}
		return pricingStrategyRepository;
	}
}
