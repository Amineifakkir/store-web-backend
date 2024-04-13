package com.example.sales.product;

import jakarta.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name ="product_sequence",
            sequenceName ="product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long id;
    private String type;

    private String title;

    @Column(name="ref",unique = true)
    private String ref;
    @Column(name="description")
    private String desc;
    private double price;



    public Product() {
    }

    public Product(long id,
                   String type,
                   String ref,
                   String title,
                   String desc,
                   double price) {
        this.id = id;
        this.type = type;
        this.ref = ref;
        this.title =title;
        this.desc = desc;
        this.price = price;
    }

    public Product(String type,
                   String ref,
                   String desc,
                   String title,
                   double price) {
        this.type = type;
        this.ref = ref;
        this.desc = desc;
        this.title =title;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", ref='" + ref + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price + '\'' +
                ", title=" + title +
                '}';
    }
}
