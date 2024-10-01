package com.manggom.play;

import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.UserDTO;

import java.sql.Connection;
import java.util.ArrayList;

import static com.manggom.common.JDBCTemplate.getConnection;

public class SelectAllUser {

    public void selectAllUser() {
        Connection con = getConnection();

        UserDAO resistDAO = new UserDAO();
        ArrayList<UserDTO> select = new ArrayList<>();
        select = resistDAO.selectAllUser(con);
        for (UserDTO user : select) {
            System.out.println("user = " + user);
        }
    }

}
