package net.spark.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.spark.app.BeanLocator;
import net.spark.backend.ProductRepository;
import net.spark.backend.WineRepository;
import net.spark.backend.data.entity.Product;
import net.spark.cellar.Wine;

@Service
public class WineService implements CrudService<Wine> {

	@Override
	public Page<Wine> findAnyMatching(Optional<String> filter, Pageable pageable) {
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
	public WineRepository getRepository() {
		return BeanLocator.find(WineRepository.class);
	}

	@Override
	public Page<Wine> find(Pageable pageable) {
		return getRepository().findBy(pageable);
	}
}
