package com.manggom.play;

import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.ExitDTO;
import com.manggom.model.dto.UserDTO;

import java.sql.Connection;
import java.util.Scanner;

import static com.manggom.common.JDBCTemplate.getConnection;

public class InputUserInfo {

    Scanner sc = new Scanner(System.in);

    public void start() {

//        페이지 시작

        for (int i = 0; true; i++) {
            System.out.println("안녕하세요. 망곰이 굿즈 구매사이트입니다.");
            System.out.print("구매를 원하신다면 1, 재구매를 하신다면 2, 나가기를 원하신다면 3을 입력해주세요. : ");
            int choice = sc.nextInt();
            if (choice == 1) {
                inputUserInfo();
            } else if (choice == 2) {
                rePurchaseUser();

            } else if (choice == 3) {
                ExitDTO exit = new ExitDTO();
                exit.setExit(1);
                break;
            } else {
                System.out.println("잘못입력하셨습니다.");
            }
        }

    }

    public void inputUserInfo() {

//        신규 정보 등록

        Connection con = getConnection();
        UserDAO resistDAO = new UserDAO();

        for (int i = 0; true; i++) {
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
            if (userName != "" && userPhone != "" && userAddr != "") {
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
            } else {
                System.out.println();
                System.out.println("정보를 다시 입력해주세요.");
                System.out.println();
            }

        }
    }

    public void rePurchaseUser() {

    //  기존고객 조회
        Connection con = getConnection();
        UserDAO resistDAO = new UserDAO();
        UserDTO user = new UserDTO();



            System.out.println();
            System.out.print("이름을 입력해주세요. : ");
            sc.nextLine();
            String userName = sc.nextLine();
            System.out.print("전화번호를 입력해주세요.(-포함) : ");
            String userPhone = sc.nextLine();

            if (userName != "" && userPhone != "") {
                user = resistDAO.comparisonUserInfo(con, userName, userPhone);
                if(user != null) {
                    resistDAO.inputUserInfo(con, user);
                    System.out.println("조회에 성공했습니다.");
                }else{
                    System.out.println("정보가 없습니다.");
                }

            } else {
                System.out.println();
                System.out.println("정보를 잘못입력했습니다.");
                System.out.println();
            }




    }
}
