package com.manggom.model.dto;

public class UserDTO {

    private int userNo;
    private String userName;
    private String userPhone;
    private String customerEmail;
    private String customerAddr;

    public UserDTO() {}

    public UserDTO(int userNo, String userName, String userPhone, String customerEmail, String customerAddr) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPhone = userPhone;
        this.customerEmail = customerEmail;
        this.customerAddr = customerAddr;
    }

    @Override
    public String toString() {
        return userNo + " . " +
                "고객이름 = " + userName +
                ", 전화번호 = " + userPhone +
                ", 이메일 = " + customerEmail +
                ", 주소 = " + customerAddr
                ;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }
}
