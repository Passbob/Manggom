package com.manggom.manngomControl;

import com.manggom.model.dao.ProductDAO;
import com.manggom.model.dao.SaleInfoDAO;
import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.SaleInfoDTO;
import com.manggom.model.dto.UserDTO;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

import static com.manggom.common.JDBCTemplate.getConnection;

public class InformationPage {

    Scanner sc = new Scanner(System.in);

    public int startInformation(int choiceProductCode, int choiceProductCount, int totalPrice, int restCount, int exitNum) {

//        페이지 시작
        int choice = 0;


        System.out.println("구매를 확정하기 위해서는 고객 정보가 필요합니다.");
        System.out.println("1. 신규회원, 2. 기존회원, "+(exitNum+1)+". 나가기");
        choice = sc.nextInt();
        if(choice == 1){
            inputUserInfo(choiceProductCode, choiceProductCount, totalPrice, restCount);
        } else if (choice == 2) {
            rePurchaseUser(choiceProductCode, choiceProductCount, totalPrice, restCount);
        } else if(choice != exitNum){
            System.out.println("숫자를 잘못입력했습니다.");
        }

        return choice;
    }


    public void inputUserInfo(int choiceProductCode, int choiceProductCount, int totalPrice , int restCount) {

//        신규 정보 등록

        Connection con = getConnection();
        UserDAO resistUserDAO = new UserDAO();
        SaleInfoDAO resistSaleDAO = new SaleInfoDAO();
        ProductDAO resistProductDAO = new ProductDAO();
        LocalDate a = LocalDate.now();
        String date = String.valueOf(a);
        System.out.println("제품을 구매하기 위한 기본 정보를 입력해주세요. ");
        System.out.println();
        System.out.print("이름을 입력해주세요. : ");
        sc.nextLine();
        String userName = sc.nextLine();
        System.out.print("전화번호를 입력해주세요.(-포함) : ");
        String userPhone = sc.nextLine();
        System.out.print("이메일을 입력해주세요. : ");
        String userEmail = sc.nextLine();
        if(userEmail.equals("")){
            userEmail = null;
        }
        System.out.print("주소를 입력해주세요. : ");
        String userAddr = sc.nextLine();
        if (userName != "" && userPhone != "" && userAddr != "") {
            int userNo = 0;
            int resultUser = 0;
            int resultSale = 0;
            UserDTO inputUser = new UserDTO(userNo, userName, userPhone, userEmail, userAddr);
            System.out.println("구매를 확정을 하시겠습니까? (Y/N)");
            String choice = sc.nextLine();
            if(choice.equals("Y")){
                resultUser = resistUserDAO.inputUserInfo(con, inputUser);
//                가장 마지막 유저번호 = 지금 등록한 유저의 번호
                int saleUserNo = resistUserDAO.selectLastUserNo(con);
                SaleInfoDTO saleInfo = new SaleInfoDTO(0, saleUserNo, date, choiceProductCode, choiceProductCount, totalPrice);
                resultSale = resistSaleDAO.inputSaleInfo(con, saleInfo);
                resistProductDAO.updateProductCount(con, choiceProductCode, restCount-choiceProductCount);
                if (resultUser > 0 && resultSale > 0) {
                    System.out.println();
                    System.out.println("등록에 성공했습니다.");
                    System.out.println();

                } else {
                    System.out.println("등록에 실패했습니다.");
                }
            }
        } else {
            System.out.println();
            System.out.println("정보를 다시 입력해주세요.");
            System.out.println();
        }


    }

    public void rePurchaseUser(int choiceProductCode, int choiceProductCount, int totalPrice, int restCount) {

        //  기존고객 조회
        Connection con = getConnection();
        UserDAO resistUserDAO = new UserDAO();
        SaleInfoDAO resistSaleDAO = new SaleInfoDAO();
        ProductDAO resistProductDAO = new ProductDAO();
        UserDTO user = new UserDTO();
        LocalDate a = LocalDate.now();
        String date = String.valueOf(a);
        int resultSale = 0;

        System.out.println("기존 정보를 조회하기 위해 입력해주세요. ");
        System.out.println();
        System.out.print("이름을 입력해주세요. : ");
        sc.nextLine();
        String userName = sc.nextLine();
        System.out.print("전화번호를 입력해주세요.(-포함) : ");
        String userPhone = sc.nextLine();

        if (userName != "" && userPhone != "") {
            user = resistUserDAO.comparisonUserInfo(con, userName, userPhone);
            if (user != null) {
                System.out.println("조회에 성공했습니다.");
                System.out.println();
                System.out.println("구매를 확정을 하시겠습니까? (Y/N)");
                String choice = sc.nextLine();
                if (choice.equals("Y")) {
                        SaleInfoDTO saleInfo = new SaleInfoDTO(0, user.getUserNo(), date, choiceProductCode, choiceProductCount, totalPrice);
                        resultSale = resistSaleDAO.inputSaleInfo(con, saleInfo);
                        resistProductDAO.updateProductCount(con, choiceProductCode, restCount-choiceProductCount);
                        if (resultSale > 0) {
                            System.out.println();
                            System.out.println("등록에 성공했습니다.");
                            System.out.println();
                        }
                } else {
                        System.out.println("정보가 없습니다.");
                }
            } else {
                    System.out.println();
                    System.out.println("정보를 잘못입력했습니다.");
                    System.out.println();
            }
        }
    }

}
