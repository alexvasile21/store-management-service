package store.management.repository;

import org.springframework.data.jpa.repository.Query;
import store.management.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c where c.name = ?1")
    public Optional<CategoryEntity> findByName(String name);
}
