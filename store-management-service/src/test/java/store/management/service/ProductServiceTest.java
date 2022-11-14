package store.management.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import store.management.dto.Category;
import store.management.dto.Product;
import store.management.error.InvalidRequestException;
import store.management.error.ProductNotFoundException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    public static final String BREAD = "bread";
    public static final String COLA = "cola";
    public static final String PEPSI = "pepsi";
    public static final String FOOD = "food";
    public static final String DRINK = "Drink";
    public static final String EMPTY_STATUS = "empty status";
    public static final String EMPTY_CATEGORY = "empty category";
    public static final String EMPTY_NAME = "empty name";
    @Autowired
    private ProductService service;

    @Test
    public void shouldAddProductWhenInputIsValid() {
        Product product = new Product();
        product.setName(COLA);
        product.setStatus(Product.StatusEnum.AVAILABLE);
        product.setCategory(new Category().name(DRINK));
        service.add(product);

        assertEquals(product.getName(), service.add(product).getName());
    }

    @Test
    public void shouldFailAddProductForInvalidName() {
        Product product = new Product();
        product.setStatus(Product.StatusEnum.SOLD);
        product.setCategory(new Category().name(DRINK));

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> service.add(product));

        assertTrue(exception.getMessage().contains(EMPTY_NAME));
    }

    @Test
    public void shouldFailAddProductForInvalidCategory() {
        Product product = new Product();
        product.setName(BREAD);
        product.setStatus(Product.StatusEnum.SOLD);
        product.setCategory(new Category());

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> service.add(product));

        assertTrue(exception.getMessage().contains(EMPTY_CATEGORY));
    }

    @Test
    public void shouldFailAddProductForInvalidStatus() {
        Product product = new Product();
        product.setName(COLA);
        product.setCategory(new Category().name(FOOD));

        InvalidRequestException exception = assertThrows(InvalidRequestException.class, () -> service.add(product));

        assertTrue(exception.getMessage().contains(EMPTY_STATUS));
    }

    @Test
    public void shouldDeleteProductWhenProductExists() {
        Product product = new Product();
        product.setName(COLA);
        product.setStatus(Product.StatusEnum.AVAILABLE);
        product.setCategory(new Category().name(DRINK));
        Product productAdded = service.add(product);

        service.deleteProduct(productAdded.getId());
    }

    @Test
    public void shoulThrowErrorWhenProductDoesNotExist() {
        assertThrows(ProductNotFoundException.class, () -> service.getProductDetails(1L));
    }

    @Test
    public void shouldUpdateProductWhenProductExists() {
        Product product = new Product();
        product.setName(COLA);
        product.setStatus(Product.StatusEnum.AVAILABLE);
        product.setCategory(new Category().name(DRINK));
        Product productAdded = service.add(product);

        Product updatedProduct = service.update(new Product().id(productAdded.getId()).status(Product.StatusEnum.PENDING));

        assertEquals(productAdded.status(Product.StatusEnum.PENDING), updatedProduct);
    }

    @Test
    public void shouldFaildUpdateProductWhenProductDoesNotExist() {
        assertThrows(ProductNotFoundException.class, () -> service.update(new Product().id(1L).status(Product.StatusEnum.PENDING)));
    }

    @Test
    public void shouldReturnListOfProducts() {
        Product product = new Product();
        product.setStatus(Product.StatusEnum.AVAILABLE);
        product.setCategory(new Category().name(DRINK));
        Product productAdded1 = service.add(product.name(COLA));
        Product productAdded2 = service.add(product.name(PEPSI));

        assertEquals(List.of(productAdded1, productAdded2), service.findProductsByStatus(Product.StatusEnum.AVAILABLE.getValue()));
    }

    @Test
    public void shouldReturnEmptyListWhenNoProductHasStatusDesired() {
        assertEquals(Collections.emptyList(), service.findProductsByStatus(Product.StatusEnum.AVAILABLE.getValue()));
    }
}