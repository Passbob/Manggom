package com.manggom.model.dao;

import com.manggom.model.dto.ProductDTO;
import com.manggom.model.dto.SaleInfoDTO;
import com.manggom.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
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

    public ArrayList<SaleInfoDTO> selectAllSaleInfo(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<SaleInfoDTO> saleInfoList =  new ArrayList<>();

        String query = prop.getProperty("selectAllSaleInfo");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            saleInfoList = new ArrayList<>();

            while(rset.next()){
                SaleInfoDTO saleInfo = new SaleInfoDTO(rset.getInt("SALE_NO"),
                        rset.getInt("USER_NO"),
                        rset.getString("SALE_DATE"),
                        rset.getInt("PRODUCT_CODE"),
                        rset.getInt("SALE_COUNT"),
                        rset.getInt("SALE_PRICE"));
                saleInfoList.add(saleInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }

        return saleInfoList;
    }

    public ArrayList<SaleInfoDTO> selectUserSaleInfo(Connection con, String userNo){
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        ArrayList<SaleInfoDTO> saleInfoList =  new ArrayList<>();

        String query = prop.getProperty("selectUserSaleInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userNo);
            rset = pstmt.executeQuery();
            saleInfoList = new ArrayList<>();

            while(rset.next()){
                SaleInfoDTO saleInfo = new SaleInfoDTO(rset.getInt("SALE_NO"),
                        rset.getInt("USER_NO"),
                        rset.getString("SALE_DATE"),
                        rset.getInt("PRODUCT_CODE"),
                        rset.getInt("SALE_COUNT"),
                        rset.getInt("SALE_PRICE"));
                saleInfoList.add(saleInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }

        return saleInfoList;
    }

}
