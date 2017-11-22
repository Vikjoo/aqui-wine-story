package net.spark.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.spark.app.BeanLocator;
import net.spark.backend.PaymentConditionRepository;
import net.spark.backend.ProductRepository;
import net.spark.backend.data.entity.PaymentCondition;
import net.spark.backend.data.entity.Product;

@Service
public class PaymentConditionService  implements CrudService<PaymentCondition> {

	@Override
	public Page<PaymentCondition> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByNameLikeIgnoreCase(repositoryFilter, pageable);
		} else {
			return find(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByNameLikeIgnoreCase(repositoryFilter);
		} else {
			return count();
		}
	}

	@Override
	public PaymentConditionRepository getRepository() {
		return BeanLocator.find(PaymentConditionRepository.class);
	}

	@Override
	public Page<PaymentCondition> find(Pageable pageable) {
		return getRepository().findBy(pageable);
	}
	}