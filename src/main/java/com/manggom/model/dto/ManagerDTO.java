package com.manggom.model.dto;

public class ManagerDTO extends UserDTO{

    private String managerCode;

    public ManagerDTO(){}

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
}
