package com.manggom.model.dao;

import com.manggom.model.dto.SaleInfoDTO;
import com.manggom.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.manggom.common.JDBCTemplate.close;

public class SaleInfoDAO {

    Properties prop = new Properties();
    public SaleInfoDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/manggom/mapper/saleInfo-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int inputSaleInfo(Connection con, SaleInfoDTO sale){

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertSaleInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,sale.getSaleNo());
            pstmt.setInt(2,sale.getUserNo());
            pstmt.setString(3,sale.getSaleDate());
            pstmt.setInt(4,sale.getProductCode());
            pstmt.setInt(5,sale.getSaleCount());
            pstmt.setInt(6,sale.getSalePrice());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }

        return result;
    }

}
