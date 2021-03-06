package net.spark.backend;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import net.spark.backend.data.entity.Product;
import net.spark.backend.data.entity.ProductWine;

public interface ProductWineRepository extends JpaRepository<ProductWine, Long> {

	Page<ProductWine> findBy(Pageable page);

	//Page<ProductWine> findByNameLikeIgnoreCase(String name, Pageable page);

	//int countByNameLikeIgnoreCase(String name);

}
