package com.manggom.model.dao;

import com.manggom.model.dto.ProductDTO;
import com.manggom.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.manggom.common.JDBCTemplate.close;
import static com.manggom.common.JDBCTemplate.getConnection;

public class ProductDAO {
    Properties prop = new Properties();
    public ProductDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/manggom/mapper/product-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public ArrayList<ProductDTO> selectAllProduct(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<ProductDTO> productList =  new ArrayList<>();

        String query = prop.getProperty("selectAllProduct");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            productList = new ArrayList<>();

            while(rset.next()){
                ProductDTO product = new ProductDTO(rset.getString("PRODUCT_CODE"),
                        rset.getString("PRODUCT_NAME"),
                        rset.getInt("PRODUCT_COUNT"),
                        rset.getInt("PRODUCT_PRICE"));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }

        return productList;
    }

    public int updateProductCount(Connection con, int productCode, int productCount){
        PreparedStatement pstmt = null;

        String query = prop.getProperty("updateProductCount");
        int result = 0;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, productCount);
            pstmt.setInt(2, productCode);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

}
