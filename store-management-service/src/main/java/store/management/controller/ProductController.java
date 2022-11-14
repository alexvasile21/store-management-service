package store.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.management.dto.Product;
import store.management.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") Long id) {
        return service.getProductDetails(id);
    }

    @PostMapping()
    public Product addProduct(@Valid @RequestBody Product product) {
        return service.add(product);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        service.deleteProduct(id);
    }

    @PutMapping()
    public Product updateProduct(@Valid @RequestBody Product product) {
        return service.update(product);
    }

    @GetMapping("/findByStatus")
    public List<Product> findByStatus(@Valid @RequestParam String status) {
        return service.findProductsByStatus(status);
    }

}
