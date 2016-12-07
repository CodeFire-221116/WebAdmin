package ua.com.codefire.cms.db.entity;

import javax.persistence.*;

/**
 * Created by User on 07.12.2016.
 */
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_type")
    private String type;
    @Column(name = "product_brand")
    private String brand;
    @Column(name = "product_model")
    private String model;
    @Column(name = "product_price")
    private Double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "product type='" + type + '\'' +
                ", id=" + id +
                '}';
    }
}
