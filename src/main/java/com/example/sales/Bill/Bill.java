package com.example.sales.Bill;

import com.example.sales.client.Client;
import com.example.sales.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
public class Bill {
    @Id
    @SequenceGenerator(
            name="bill_sequence",
            sequenceName = "bill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "bill_sequence"
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;
    private Long quantity;
    @Transient
    private double total;
    @Transient
    private String clientName;
    @Transient
    private String productRef;

    public Bill() {
    }

    public Bill(Client client, Product product, Long quantity) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public Bill(Long id, Client client, Product product, Long quantity) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {

        return this.quantity * this.product.getPrice();
    }

    public  String getClientName(){
        return this.client.getNom() + " " + this.client.getPrenom();
    }
    public String getProductRef(){
        return this.product.getDesc();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", quantity=" + quantity +
                ", total=" + total +
                '}';
    }
}
