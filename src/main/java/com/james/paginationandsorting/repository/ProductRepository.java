package com.james.paginationandsorting.repository;

import com.james.paginationandsorting.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
