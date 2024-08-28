package com.james.paginationandsorting.service;

import com.james.paginationandsorting.dto.ProductDto;
import com.james.paginationandsorting.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    ResponseEntity<Product> createProduct(ProductDto productDto);

    ResponseEntity<Product> updateProduct(Long id, ProductDto productDto);

    Product getProductById(Long id);

    List<Product> findProductsWithSorting(String field);

    Page<Product> findProductsWithPagination(int offset, int pageSize);

    Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field);

    List<Product> sortBasedOnSomeField(String field);

    Page<Product> getProductWithPagination(int offset, int pageSize);

    Page<Product> getProductWithPaginationAndSorting(int offset, int pageSize, String field);
}
