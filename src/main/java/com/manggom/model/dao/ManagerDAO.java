package com.manggom.model.dao;

import com.manggom.model.dto.ManagerDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.manggom.common.JDBCTemplate.close;

public class ManagerDAO {
    Properties prop = new Properties();
    public ManagerDAO(){
            try {
                prop.loadFromXML(new FileInputStream("src/main/java/com/manggom/mapper/manager-query.xml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    public boolean checkManager(Connection con, String password){
        boolean isPass = false;
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectManagerCode");

        List<String> passList;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            passList = new ArrayList<>();
            while (rset.next()){
                passList.add(rset.getString("MANAGER_CODE"));
            }
            for(int i = 0; passList.size() > i; i++) {
              isPass = passList.get(i).equals(password);
              if(isPass == true){
                  break;
              }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }
        return isPass;
    }

    public int inputManager(Connection con, ManagerDTO newManager){
        int result = 0;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("inputManager");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, newManager.getManagerNo());
            pstmt.setString(2, newManager.getManagerCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }

        return result;
    }

    public int updateManagerCode(Connection con, int managerNo, String managerCode){
        int result = 0;
        PreparedStatement pstmt = null;

        String query = prop.getProperty("updateManagerCode");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, managerCode);
            pstmt.setInt(2, managerNo);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }


        return result;
    }

}
