package dev.patika.ecommerce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "product_name")
    private String name; //ürün adı

    @Column(name = "product_price")
    private double prc; //ürün fiyatı

    @Column(name = "product_stock")
    private int stock; //stok bilgisi

    @ManyToOne
    @JoinColumn(name = "product_supplier_id", referencedColumnName = "supplier_id")
    private Supplier supplier; //Supplier ile Product arasında Foreign Key ilişkisi var

    @ManyToOne
    @JoinColumn(name = "product_category_id", referencedColumnName = "category_id")
    private Category category; //Category ile Product arasında Foreign Key ilişkisi var
}
