package com.manggom.model.dao;

import com.manggom.model.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.manggom.common.JDBCTemplate.close;

public class UserDAO {

    Properties prop = new Properties();

    public UserDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/manggom/mapper/user-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int inputUserInfo(Connection con, UserDTO user){

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertUser");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,0);
            pstmt.setString(2,user.getUserName());
            pstmt.setString(3,user.getUserPhone());
            pstmt.setString(4,user.getCustomerEmail());
            pstmt.setString(5,user.getCustomerAddr());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
        }

        return result;
    }

    public ArrayList<UserDTO> selectAllUser(Connection con){
        Statement stmt = null;
        ResultSet rset = null;

        ArrayList<UserDTO> userList = null;

        String query = prop.getProperty("selectAllUser");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            userList = new ArrayList<>();

            while(rset.next()){
                UserDTO user = new UserDTO(rset.getInt("USER_NO"),
                         rset.getString("USER_NAME"),
                         rset.getString("USER_PHONE"),
                         rset.getString("CUSTOMER_EMAIL"),
                         rset.getString("CUSTOMER_ADDR"));
                userList.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }

        return userList;
    }
    public UserDTO comparisonUserInfo(Connection con, String userName, String userPhone) {
        Statement stmt = null;
        ResultSet rset = null;
        UserDTO user = new UserDTO();

        List<Map<String, String>> comparisonList = null;

        String query = prop.getProperty("selectUserNameAndPhone");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            Map<String , String> comparison = new HashMap<>();
            comparison.put(userName, userPhone);
            comparisonList = new ArrayList<>();
            System.out.println("comparison = " + comparison);

            while (rset.next()) {
                Map<String , String> userMap = new HashMap<>();
                userMap.put(rset.getString("USER_NAME"), rset.getString("USER_PHONE"));

                comparisonList.add(userMap);
            }
            for(Map<String, String> map : comparisonList){
                System.out.println("map = " + map);
                if(map.equals(comparison)){
                    user = selectUserName(con, userName);
                }else{
                    user = null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(stmt);
            close(rset);
        }

        return user;
    }

    public UserDTO selectUserName(Connection con, String userName) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectUserName");

        UserDTO user = null;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,userName);

            rset = pstmt.executeQuery();


            if(rset.next()){
                user = new UserDTO(rset.getInt("USER_NO"),
                        rset.getString("USER_NAME"),
                        rset.getString("USER_PHONE"),
                        rset.getString("CUSTOMER_EMAIL"),
                        rset.getString("CUSTOMER_ADDR"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }

        return user;
    }

}



