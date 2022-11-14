package store.management.model;

import store.management.dto.Product;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PRODUCTS")
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private CategoryEntity category;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Product.StatusEnum status;

    @Column(name = "LAST_UPDATED", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public Product.StatusEnum getStatus() {
        return status;
    }

    public void setStatus(Product.StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}


