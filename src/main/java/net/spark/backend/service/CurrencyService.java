package net.spark.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.spark.app.BeanLocator;
import net.spark.backend.CurrencyRepository;
import net.spark.backend.PickupLocationRepository;
import net.spark.backend.data.entity.Currency;
import net.spark.backend.data.entity.PickupLocation;

@Service
public class CurrencyService {

	private CurrencyRepository currencyRepository;

	public Page<Currency> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getCurrencyRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getCurrencyRepository().findByNameLikeIgnoreCase("%", pageable);
		}
	}

	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getCurrencyRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getCurrencyRepository().countByNameLikeIgnoreCase("%");
		}
	}

	public Currency getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	protected CurrencyRepository getCurrencyRepository() {
		if (currencyRepository == null) {
			currencyRepository = BeanLocator.find(CurrencyRepository.class);
		}
		return currencyRepository;
	}
}
