package store.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import store.management.dto.Product;
import store.management.model.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p from ProductEntity p where p.status = ?1")
    List<ProductEntity> findAllByStatus(Product.StatusEnum status);
}
