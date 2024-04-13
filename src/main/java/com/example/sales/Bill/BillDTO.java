package com.example.sales.Bill;

public class BillDTO {
    private Long clientId;
    private Long productId;
    private Long quantity;

    public BillDTO() {
    }
    public BillDTO(Long clientId, Long productId, Long quantity) {
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
