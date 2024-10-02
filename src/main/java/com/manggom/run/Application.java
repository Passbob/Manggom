package com.manggom.run;


import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.UserDTO;
import com.manggom.play.SaleProductPage;

import java.sql.Connection;
import java.time.LocalDate;

import static com.manggom.common.JDBCTemplate.getConnection;


public class Application {

    public static void main(String[] args) {
        SaleProductPage start = new SaleProductPage();
        Connection con = getConnection();
//        SelectAllUser se = new SelectAllUser();
//        se.selectAllUser();
          start.startPurchase();
//        SaleProductPage a = new SaleProductPage();
//        a.productSale();
//        UserDAO a = new UserDAO();
//        int b = a.selectLastUserNo(con);
//        System.out.println("b = " + b);


    }
}
