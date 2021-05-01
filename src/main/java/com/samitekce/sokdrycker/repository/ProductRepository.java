package com.samitekce.sokdrycker.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.samitekce.sokdrycker.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	List<Product> findByEan(String ean);

	List<Product> findProductsByKeepsEcodesCode(String string, Sort sort);

	List<Product> findProductsByKeepsEcodesCode(String ecode);

	List<Product> findProductsByKeepsEcodesCodeNot(String ecode);

	List<Product> findByNameIsContaining(String name);

	List<Product> sugarIsLessThanEqualOrderBySugar(double level);

	void deleteByEan(String id);

}
