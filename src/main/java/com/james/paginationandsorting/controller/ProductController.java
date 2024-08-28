package com.james.paginationandsorting.controller;

import com.james.paginationandsorting.dto.ProductDto;
import com.james.paginationandsorting.dto.Response;
import com.james.paginationandsorting.model.Product;
import com.james.paginationandsorting.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/insert")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        return  productService.createProduct(productDto);
    }

    @GetMapping("/find/{id}")
    public Product getProductById(@PathVariable Long id) {
        return  productService.getProductById(id);
    }

    @GetMapping("get_all")
    public Response<List<Product>> getProducts() {
        return (Response<List<Product>>) productService.getAllProducts();
    }

    @PutMapping("update_product")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        return productService.updateProduct(id, productDto);
    }

    @GetMapping("/{field}")
    public Response<List<Product>> getProductsWithSort(@PathVariable String field) {
        return (Response<List<Product>>) productService.findProductsWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Response<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
        return new Response<>(productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public Response<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new Response<>(productsWithPagination);
    }

    @GetMapping("/{field1}")
    private List<Product> getProductsWithSort2(@PathVariable String field) {
        List<Product> allProducts = productService.findProductsWithSorting(field);
        return allProducts;
    }

    @GetMapping("/pagination1/{offset}/{pageSize}")
    private Page<Product> getProductsWithPagination2(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
        return productsWithPagination;
    }

    @GetMapping("/paginationAndSort1/{offset}/{pageSize}/{field}")
    private Page<Product> getProductsWithPaginationAndSort2(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return productsWithPagination ;
    }
}
