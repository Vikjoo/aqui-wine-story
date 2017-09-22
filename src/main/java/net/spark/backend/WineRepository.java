package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.Product;
import net.spark.cellar.Wine;

public interface WineRepository extends JpaRepository<Wine, Long> {

	Page<Wine> findBy(Pageable page);

	Page<Wine> findByNameLikeIgnoreCase(String name, Pageable page);

	int countByNameLikeIgnoreCase(String name);

}
