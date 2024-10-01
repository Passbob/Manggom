package com.manggom.model.dto;

public class TotalSaleDTO {

    private String productCode;
    private int salesVolume;
    private int restCount;
    private int salesAmount;

    public TotalSaleDTO(){}

    public TotalSaleDTO(String productCode, int salesVolume, int restCount, int salesAmount) {
        this.productCode = productCode;
        this.salesVolume = salesVolume;
        this.restCount = restCount;
        this.salesAmount = salesAmount;
    }

    @Override
    public String toString() {
        return "TotalSaleDTO{" +
                "productCode='" + productCode + '\'' +
                ", salesVolume=" + salesVolume +
                ", restCount=" + restCount +
                ", salesAmount=" + salesAmount +
                '}';
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getRestCount() {
        return restCount;
    }

    public void setRestCount(int restCount) {
        this.restCount = restCount;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }
}
