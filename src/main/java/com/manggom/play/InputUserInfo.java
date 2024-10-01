package com.manggom.play;

import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.ExitDTO;
import com.manggom.model.dto.UserDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import static com.manggom.common.JDBCTemplate.getConnection;

public class InputUserInfo {

    Scanner sc = new Scanner(System.in);

    public void inputUserInfo(){
        Connection con = getConnection();
        UserDAO resistDAO = new UserDAO();

        for (int i = 0; true; i++) {
            System.out.println("안녕하세요. 망곰이 굿즈 구매사이트입니다.");
            System.out.print("구매를 원하신다면 1, 나가기를 원하신다면 2를 입력해주세요. : ");
            int choice = sc.nextInt();
            if (choice == 2) {
                ExitDTO exit = new ExitDTO();
                exit.setExit(1);
                break;
            }
            System.out.println();
            System.out.print("이름을 입력해주세요. : ");
            sc.nextLine();
            String userName = sc.nextLine();
            System.out.print("전화번호를 입력해주세요.(-포함) : ");
            String userPhone = sc.nextLine();
            System.out.print("이메일을 입력해주세요. : ");
            String userEmail = sc.nextLine();
            System.out.print("주소를 입력해주세요. : ");
            String userAddr = sc.nextLine();
            if(userName != "" && userPhone != "" && userAddr != "" ) {
                int userNo = 0;
                UserDTO inputUser = new UserDTO(userNo, userName, userPhone, userEmail, userAddr);

                int result = resistDAO.inputUserInfo(con, inputUser);

                if (result > 0) {
                    System.out.println();
                    System.out.println("등록에 성공했습니다.");
                    System.out.println();
                    break;
                } else {
                    System.out.println("등록에 실패했습니다.");
                }
            }else {
                System.out.println();
                System.out.println("정보를 다시 입력해주세요.");
                System.out.println();
            }
        }


    }
}
