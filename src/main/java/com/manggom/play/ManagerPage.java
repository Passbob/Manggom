package com.manggom.play;

import com.manggom.model.dao.ManagerDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.manggom.common.JDBCTemplate.getConnection;

public class ManagerPage {
    Scanner sc = new Scanner(System.in);

    public void managerPassword(){
        Connection con = getConnection();

        System.out.println("비밀번호를 입력하세요. ");
        String password = sc.nextLine();
        ManagerDAO manager = new ManagerDAO();
        boolean isPass = manager.checkManager(con, password);

        if(isPass){
            selectTable();
        }else{
            System.out.println("비밀번호가 틀렸습니다.");
        }

    }

    public void selectTable(){

        for (int i = 0; true; i++) {
            System.out.println("1. 물품  2. 회원  3. 판매현황  4. 나가기");
            System.out.println("번호를 입력하세요. ");
            int choice = sc.nextInt();
            if (choice == 4) {
                System.out.println("매니저모드를 종료합니다.");
            } else {
                switch (choice) {
                    case 1:
                        userControl();
                        break;
                    case 2:
                        productControl();
                        break;
                    case 3:
                        saleInfoControl();
                        break;
                }
            }
        }

    }

    public void userControl(){
        Connection con = getConnection();
//        모든 유저 검색
//        이름으로 유저 검색
//        전화번호로 유저 검색
//        유저 삭제
    }

    public void productControl(){
        Connection con = getConnection();
//        모든 물품 검색
//        남은 재고량 검색
//        코드로 검색
//        물품 삭제
//        물품 삽입
    }

    public void saleInfoControl(){
        Connection con = getConnection();
//        모든 거래 검색
//        유저별 총 거래
//        상품별 총 거래
//        거래 총합
//        거래 제거
    }

}
