package net.spark.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.spark.app.BeanLocator;
import net.spark.backend.PaymentTermRepository;
import net.spark.backend.data.entity.PaymentTerm;

@Service
public class PaymentTermService {

	private PaymentTermRepository paymentTermRepository;

	public Page<PaymentTerm> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getPaymentTermRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return getPaymentTermRepository().findByNameLikeIgnoreCase("%", pageable);
		}
	}

	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getPaymentTermRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return getPaymentTermRepository().countByNameLikeIgnoreCase("%");
		}
	}

	public PaymentTerm getDefault() {
		return findAnyMatching(Optional.empty(), new PageRequest(0, 1)).iterator().next();
	}

	protected PaymentTermRepository getPaymentTermRepository() {
		if (paymentTermRepository == null) {
			paymentTermRepository = BeanLocator.find(PaymentTermRepository.class);
		}
		return paymentTermRepository;
	}
}
