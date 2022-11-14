package store.management.service;

import store.management.dto.Category;
import store.management.dto.Product;
import store.management.model.CategoryEntity;
import store.management.model.ProductEntity;

public class MappersUtil {

    public static Product mapToProductDto(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setCategory(mapToCategoryDto(productEntity.getCategory()));
        product.setName(productEntity.getName());
        product.setStatus(productEntity.getStatus());

        return product;
    }

    public static ProductEntity mapToProductEntity(Product productDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDto.getName());
        productEntity.setStatus(productDto.getStatus());
        productEntity.setCategory(mapToCategoryEntity(productDto.getCategory()));

        return productEntity;
    }

    public static Category mapToCategoryDto(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getName());

        return category;
    }

    public static CategoryEntity mapToCategoryEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());

        return categoryEntity;
    }
}
