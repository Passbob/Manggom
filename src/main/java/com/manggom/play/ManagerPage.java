package com.manggom.play;

import com.manggom.model.dao.ManagerDAO;
import com.manggom.model.dao.ProductDAO;
import com.manggom.model.dao.SaleInfoDAO;
import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.ProductDTO;
import com.manggom.model.dto.SaleInfoDTO;
import com.manggom.model.dto.UserDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;
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
            System.out.println("=====================================");
            System.out.println("1. 회원  2. 물품  3. 판매현황  4. 나가기");
            System.out.println("번호를 입력하세요. ");
            int choice = sc.nextInt();
            if (choice == 4) {
                System.out.println("매니저모드를 종료합니다.");
                break;
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
        UserDAO resistDAO = new UserDAO();
        System.out.println("===========회원정보 조회 창===========");
        System.out.println("1. 모든 회원 검색  2. 이름으로 유저 검색");
        System.out.println("3. 전화번호로 검색  4. 유저 삭제");
        int choice = sc.nextInt();
        if(choice == 1){
//               모든 유저 검색
            ArrayList<UserDTO> select = new ArrayList<>();
            select = resistDAO.selectAllUser(con);
            for (UserDTO user : select) {
                System.out.println(user);
            }
        }else if(choice == 2) {
//            이름으로 유저 검색
            System.out.println("조회할 회원의 이름을 입력해주세요. ");
            sc.nextLine();
            String searchName = sc.nextLine();
            UserDTO select = resistDAO.selectUserName(con, searchName);
            System.out.println("user = " + select);
        }else if(choice == 3) {
//            전화번호로 유저 검색
            System.out.println("조회할 회원의 전화번호를 입력해주세요. ");
            sc.nextLine();
            String searchPhone = sc.nextLine();
            UserDTO select = resistDAO.selectUserPhone(con, searchPhone);
            System.out.println("user = " + select);
        } else if (choice == 4) {
//            유저 삭제
            System.out.println("삭제할 회원의 회원번호를 입력해주세요. ");
            sc.nextLine();
            int deleteUser = sc.nextInt();
            UserDTO select = resistDAO.selectUserNo(con, deleteUser);
            int result = resistDAO.deleteUserInfo(con, deleteUser);

            if(result > 0) {
                System.out.println();
                System.out.println(select);
                System.out.println("해당 유저의 정보가 삭제되었습니다.");
                System.out.println();
            }else {
                System.out.println("해당 회원이 존재하지 않습니다.");
            }
        }

    }

    public void productControl(){
        Connection con = getConnection();
        ProductDAO resistDAO = new ProductDAO();
        System.out.println("===========물품정보 조회 창===========");
        System.out.println(" 1. 모든 물품 검색  2. 남은 재고량 검색");
        System.out.println(" 3. 물품 삭제   4. 물품 등록");
        int choice = sc.nextInt();
        if(choice == 1) {
//        모든 물품 검색
            ArrayList<ProductDTO> select = new ArrayList<>();
            select = resistDAO.selectAllProduct(con);
            for (ProductDTO product : select) {
                System.out.println(product);
            }
        } else if (choice == 2) {
//        남은 재고량 검색
            ArrayList<Map<String , Integer>> rest = new ArrayList<>();
            rest = resistDAO.selectRestCount(con);
            for(Map<String , Integer> restProduct : rest){
                System.out.println("남은 수량 : "+restProduct);
            }
        }else if(choice == 3) {
//        물품 삭제
            System.out.println("삭제할 물품의 코드번호를 입력하세요. ");
            int deleteNum = sc.nextInt();
            int result = resistDAO.deleteProduct(con, deleteNum);
            if(result > 0) {
                System.out.println();
                System.out.println("해당 물품이 삭제되었습니다.");
                System.out.println();
            }else {
                System.out.println("해당 물품이 존재하지 않습니다.");
            }
        }else if(choice == 4) {
//        물품 삽입
            System.out.println("===물품 등록 실행===");
            System.out.println("물품의 코드번호를 입력해주세요. ");
            int productCode = sc.nextInt();
            System.out.println("물품명을 입력해주세요. ");
            sc.nextLine();
            String productName = sc.nextLine();
            System.out.println("물품의 재고를 입력해주세요. ");
            int productCount = sc.nextInt();
            System.out.println("물품의 가격을 입력해주세요. ");
            int productPrice = sc.nextInt();
            ProductDTO product = new ProductDTO(productCode, productName, productCount, productPrice);
            int result = resistDAO.inputProduct(con, product);
            if(result>0){
                System.out.println("===!!!등록 성공!!!===");
            }else {
                System.out.println("!!!===등록 실패===!!!");
            }
        }
    }

    public void saleInfoControl(){
        Connection con = getConnection();
        SaleInfoDAO resistDAO = new SaleInfoDAO();
        System.out.println("===========판매현황 조회 창===========");
        System.out.println(" 1. 모든 거래 검색  2. 유저별 총 거래");
        System.out.println(" 3. 상품별 총 거래");
        System.out.println(" 4. 거래 삭제  5. 거래 총합");
        int choice = sc.nextInt();
        if(choice == 1) {
//        모든 거래 검색
            ArrayList<SaleInfoDTO> select = new ArrayList<>();
            select = resistDAO.selectAllSaleInfo(con);
            for (SaleInfoDTO sale : select) {
                System.out.println(sale);
            }
        } else if (choice == 2) {
//        유저별 총 거래

            System.out.println("조회할 유저의 번호를 입력해주세요. ");
            String userNo=sc.nextLine();
            ArrayList<SaleInfoDTO> select = new ArrayList<>();
            select = resistDAO.selectUserSaleInfo(con, userNo);
            for (SaleInfoDTO sale : select) {
                System.out.println(sale);
            }

        }
//        상품별 총 거래
//        거래 총합
//        거래 제거
    }

}
