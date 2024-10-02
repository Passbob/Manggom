package com.manggom.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

}
