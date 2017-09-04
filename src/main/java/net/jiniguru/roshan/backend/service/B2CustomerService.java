package net.jiniguru.roshan.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.jiniguru.roshan.app.BeanLocator;
import net.jiniguru.roshan.app.security.SecurityUtils;
import net.jiniguru.roshan.backend.B2CustomerRepository;
import net.jiniguru.roshan.backend.CustomerRepository;
import net.jiniguru.roshan.backend.data.entity.B2Customer;
import net.jiniguru.roshan.backend.data.entity.Customer;

@Service
public class B2CustomerService implements CrudService<B2Customer> {

	private static final String MODIFY_LOCKED_USER_NOT_PERMITTED = "Customer has been locked and cannot be modified or deleted";
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public B2CustomerService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public Customer getCurrentCustomer() {
		return getRepository().findByEmail(SecurityUtils.getUsername());
	}

	@Override
	public Page<B2Customer> findAnyMatching(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(repositoryFilter,
					repositoryFilter,  pageable);
		} else {
			return find(pageable);
		}
	}

	@Override
	public long countAnyMatching(Optional<String> filter) {
		if (filter.isPresent()) {
			String repositoryFilter = "%" + filter.get() + "%";
			return getRepository().countByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(repositoryFilter, repositoryFilter);
		} else {
			return getRepository().count();
		}
	}

	@Override
	public B2CustomerRepository getRepository() {
		return BeanLocator.find(B2CustomerRepository.class);
	}

	@Override
	public Page<B2Customer> find(Pageable pageable) {
		return getRepository().findBy(pageable);
	}

	public String encodePassword(String value) {
		return passwordEncoder.encode(value);
	}

	@Override
	@Transactional
	public B2Customer save(B2Customer entity) {
		throwIfUserLocked(entity.getId());
		return getRepository().save(entity);
	}

	@Override
	@Transactional
	public void delete(long userId) {
		throwIfUserLocked(userId);
		getRepository().delete(userId);
	}

	private void throwIfUserLocked(Long userId) {
		if (userId == null) {
			return;
		}

		B2Customer dbUser = getRepository().findOne(userId);
		if (dbUser.isLocked()) {
			throw new UserFriendlyDataException(MODIFY_LOCKED_USER_NOT_PERMITTED);
		}
	}

}
