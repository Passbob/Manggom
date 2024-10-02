package com.manggom.model.dto;

public class ProductDTO {

    private String productCode;
    private String productName;
    private int productCount;
    private int productPrice;

    public ProductDTO(){}

    public ProductDTO(String productCode, String productName, int productCount, int productPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productCount = productCount;
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return " " + productCode +
                " . 상품명 = " + productName +
                ", 남은 수량 = " + productCount +
                ", 가격 = " + productPrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }
}
