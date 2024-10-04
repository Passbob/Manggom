package com.manggom.model.dto;

public class ManagerDTO extends UserDTO{

    private int managerNo;
    private String managerCode;

    public ManagerDTO(){}

    public ManagerDTO(String managerCode) {
        this.managerCode = managerCode;
    }

    public ManagerDTO(int managerNo, String managerCode) {
        this.managerNo = managerNo;
        this.managerCode = managerCode;
    }

    @Override
    public String toString (){
        return "userNo=" + getUserNo() + "managerCode="+managerCode;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public int getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(int managerNo) {
        this.managerNo = managerNo;
    }
}
