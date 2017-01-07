package ua.com.codefire.cms.db.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by User on 07.12.2016.
 */
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @NotBlank
    @Column(name = "product_type")
    private String productType;
    @NotBlank
    @Column(name = "product_brand")
    private String productBrand;
    @NotBlank
    @Column(name = "product_model")
    private String productModel;
    @NotBlank
    @Column(name = "product_price")
    private Double productPrice;

    public ProductEntity(Long id, String type, String brand, String model, Double price) {
        this.id = id;
        this.productType = type;
        this.productBrand = brand;
        this.productModel = model;
        this.productPrice = price;
    }

    public ProductEntity(String type, String brand, String model, Double price) {
        this.productType = type;
        this.productBrand = brand;
        this.productModel = model;
        this.productPrice = price;
    }

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String type) {
        this.productType = type;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String brand) {
        this.productBrand = brand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String model) {
        this.productModel = model;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double price) {
        this.productPrice = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity product = (ProductEntity) o;

        return id.equals(product.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
                "product type='" + productType + '\'' +
                ", id=" + id +
                '}';
    }
}
