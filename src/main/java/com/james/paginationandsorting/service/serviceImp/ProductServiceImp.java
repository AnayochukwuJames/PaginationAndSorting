package com.james.paginationandsorting.service.serviceImp;

import com.james.paginationandsorting.dto.ProductDto;
import com.james.paginationandsorting.model.Product;
import com.james.paginationandsorting.repository.ProductRepository;
import com.james.paginationandsorting.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

private final ProductRepository productRepository;

private final ModelMapper modelMapper;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ResponseEntity<Product> createProduct(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<Product> updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Product with ID " + id + " not found"));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        Product updatedProduct = productRepository.save(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findProductsWithSorting(String field){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }
    @Override
    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }

    @Override
    public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }

    @Override
    public List<Product> sortBasedOnSomeField(String field) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC,field));

    }

    @Override
    public Page<Product> getProductWithPagination(int offset, int pageSize) {
        return productRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<Product> getProductWithPaginationAndSorting(int offset, int pageSize, String field) {
        return productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }

    @PostConstruct
    public void initDB() {
        List<Product> products = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Product((long) i,"product" + i, 1000.0, new Random()
                        .nextInt(50000)))
                .collect(Collectors.toList());
        productRepository.saveAll(products);
    }
}
