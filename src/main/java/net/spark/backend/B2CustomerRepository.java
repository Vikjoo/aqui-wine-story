package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.jiniguru.roshan.backend.data.entity.B2Customer;
import net.jiniguru.roshan.backend.data.entity.Customer;
import net.jiniguru.roshan.backend.data.entity.User;

public interface B2CustomerRepository extends JpaRepository<B2Customer, Long> {
	Page<B2Customer> findBy(Pageable pageable);

	Page<B2Customer> findByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String repositoryFilter,
			String repositoryFilter2,  Pageable pageable);

	Customer findByEmail(String username);

	long countByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String repositoryFilter, String repositoryFilter2);
}
