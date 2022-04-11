package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Map;

import com.example.demo.model.CustomResponse;
import com.example.demo.model.Product;
import com.example.demo.service.facade.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomResponse> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(
                new CustomResponse(
                        LocalDateTime.now(),
                        201,
                        HttpStatus.CREATED,
                        null,
                        new String("New Product Placed"),
                        Map.of("product", productService.addProduct(product))));
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomResponse> findAll(@RequestParam(value = "page",defaultValue = "0")int page,
                                                  @RequestParam(value = "size",defaultValue = "3")int size) {

        return ResponseEntity.ok(
                new CustomResponse(
                        LocalDateTime.now(),
                        200,
                        HttpStatus.OK,
                        null,
                        "List of products",
                        Map.of("products", productService.findAll( PageRequest.of(page,size)))));
    }

}
