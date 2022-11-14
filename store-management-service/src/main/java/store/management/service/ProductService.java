package store.management.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.management.dto.Product;
import store.management.error.InvalidRequestException;
import store.management.error.InvalidStatusException;
import store.management.error.ProductNotFoundException;
import store.management.model.ProductEntity;
import store.management.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static store.management.service.MappersUtil.*;

@Service
public class ProductService {

    private static Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product add(Product product) {
        validateNewProductFields(product);
        ProductEntity productEntity = mapToProductEntity(product);

        logger.info("Saving product: {}", productEntity.toString());

        ProductEntity savedEntity = productRepository.save(productEntity);

        return mapToProductDto(savedEntity);
    }

    public Product update(Product product) {
        ProductEntity productEntity = getProductEntity(product.getId());

        if (product.getName() != null) {
            productEntity.setName(product.getName());
        }
        if (product.getStatus() != null) {
            productEntity.setStatus(product.getStatus());
        }
        if (product.getCategory() != null && product.getCategory().getName() != null) {
            productEntity.setCategory(mapToCategoryEntity(product.getCategory()));
        }

        logger.info("Updating product: {}", productEntity.toString());
        ProductEntity savedEntity = productRepository.saveAndFlush(productEntity);

        return mapToProductDto(savedEntity);
    }

    public Product getProductDetails(Long id) {
        ProductEntity productEntity = getProductEntity(id);

        return mapToProductDto(productEntity);
    }

    public void deleteProduct(Long id) {
        getProductEntity(id);

        logger.info("Deleting product with id {}", id);
        productRepository.deleteById(id);
    }

    public List<Product> findProductsByStatus(String status) {
        try {
            Product.StatusEnum statusEnum = Product.StatusEnum.fromValue(status);

            return productRepository.findAllByStatus(statusEnum).stream()
                    .map(MappersUtil::mapToProductDto)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new InvalidStatusException("Invalid status: " + status);
        }
    }


    private ProductEntity getProductEntity(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);

        if (product.isEmpty()) {
            throw new ProductNotFoundException(String.format("Product with id %s does not exist.", id));
        }

        return product.get();
    }


    private void validateNewProductFields(Product product) {
        String error = null;
        if (product.getName() == null) {
            error = "empty name";
        } else if (product.getCategory() == null || product.getCategory().getName() == null) {
            error = "empty category";
        } else if (product.getStatus() == null) {
            error = "empty status";
        }

        if (error != null) {
            throw new InvalidRequestException("Invalid request: " + error);
        }
    }
}
