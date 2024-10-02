package com.manggom.model.dto;

public class ProductDTO {

    private int productCode;
    private String productName;
    private int productCount;
    private int productPrice;

    public ProductDTO(){}

    public ProductDTO(int productCode, String productName, int productCount, int productPrice) {
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

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
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
