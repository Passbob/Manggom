package com.manggom.manngomControl;

import com.manggom.model.dao.ManagerDAO;
import com.manggom.model.dao.ProductDAO;
import com.manggom.model.dao.SaleInfoDAO;
import com.manggom.model.dao.UserDAO;
import com.manggom.model.dto.ManagerDTO;
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

        System.out.println("매니저코드를 입력하세요. ");
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
            System.out.println("1. 회원  2. 물품  3. 판매현황 4. 매니저관리 5. 나가기");
            System.out.println("번호를 입력하세요. ");
            int choice = sc.nextInt();
            if (choice == 5) {
                System.out.println("매니저모드를 종료합니다.");
                break;
            }else if(choice > 0 && choice < 5) {
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
                    case 4:
                        managerControl();
                        break;
                }
            }else {
                System.out.println("번호를 잘못입력하셨습니다.");
            }
        }

    }

    public void userControl(){
        Connection con = getConnection();
        UserDAO resistDAO = new UserDAO();
        System.out.println("===========회원정보 조회 창===========");
        System.out.println("1. 모든 회원 검색  2. 이름으로 유저 검색");
        System.out.println("3. 전화번호로 검색  4. 회원 정보 수정");
        System.out.println("5. 회원 삭제");
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
            if(select != null) {
                System.out.println("user = " + select);
            }else{
                System.out.println("해당 이름을 가진 회원이 존재하지 않습니다.");
            }
        }else if(choice == 3) {
//            전화번호로 유저 검색
            System.out.println("조회할 회원의 전화번호를 입력해주세요. ");
            sc.nextLine();
            String searchPhone = sc.nextLine();
            UserDTO select = resistDAO.selectUserPhone(con, searchPhone);
            if(select != null) {
                System.out.println("user = " + select);
            }else{
                System.out.println("해당 전화번호를 가진 회원이 존재하지 않습니다.");
            }
        } else if (choice == 4) {
//            회원 수정
            System.out.println("수정할 회원의 회원번호를 입력해주세요. ");
            sc.nextLine();
            int updateUser = sc.nextInt();
            UserDTO select = resistDAO.selectUserNo(con, updateUser);
            if(select != null) {
                System.out.println("수정할 정보를 입력해주세요. (수정을 원하지 않는다면 공백으로 Enter를 눌러주세요)");
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
                if(!userName.equals("")){
                    select.setUserName(userName);
                }
                if(!userPhone.equals("")){
                    select.setUserPhone(userPhone);
                }
                if(!userEmail.equals("")){
                    select.setCustomerEmail(userEmail);
                }
                if(!userAddr.equals("")){
                    select.setCustomerAddr(userAddr);
                }

                int result = resistDAO.updateUserInfo(con, select);

                if (result > 0) {
                    System.out.println();
                    System.out.println(select);
                    System.out.println("해당 회원의 정보가 수정되었습니다.");
                    System.out.println();
                } else {
                    System.out.println("정보 수정에 실패했습니다.");
                }
            }else{
                System.out.println("해당 회원이 존재하지 않습니다.");
            }
        } else if (choice == 5) {
//            유저 삭제
            System.out.println("삭제할 회원의 회원번호를 입력해주세요. ");
            sc.nextLine();
            int deleteUser = sc.nextInt();
            UserDTO select = resistDAO.selectUserNo(con, deleteUser);
            int result = resistDAO.deleteUserInfo(con, deleteUser);

            if(result > 0) {
                System.out.println();
                System.out.println(select);
                System.out.println("해당 회원의 정보가 삭제되었습니다.");
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
        System.out.println(" 4. 거래 총합  5. 거래 삭제");
        int choice = sc.nextInt();
        if(choice == 1) {
//        모든 거래 검색
            ArrayList<SaleInfoDTO> select = new ArrayList<>();
            select = resistDAO.selectAllSaleInfo(con);
            if(select != null) {
                for (SaleInfoDTO sale : select) {
                    System.out.println(sale);
                }
            }else {
                System.out.println("판매내역이 존재하지 않습니다.");
            }
        } else if (choice == 2) {
//        유저별 총 거래

            System.out.println("조회할 회원의 번호를 입력해주세요. ");
            int userNo = sc.nextInt();
            ArrayList<SaleInfoDTO> select = new ArrayList<>();
            select = resistDAO.selectUserSaleInfo(con, userNo);
            if(select != null) {
                for (SaleInfoDTO sale : select) {
                    System.out.println(sale);
                }
            }else {
                System.out.println("해당 회원이 존재하지 않거나 회원의 거래내역이 존재하지 않습니다.");
            }

        } else if(choice == 3) {
//        상품별 총 거래
            System.out.println("조회할 물품의 코드를 입력해주세요. ");
            int productCode = sc.nextInt();
            ArrayList<SaleInfoDTO> select = new ArrayList<>();
            select = resistDAO.selectProductSaleInfo(con, productCode);
            if(select.size() >= 1){
                for(SaleInfoDTO sale : select){
                    System.out.println(sale);
                }
            }else {
                System.out.println("해당 물품의 판매내역이 존재하지 않습니다.");
            }
        } else if(choice == 4) {
//        거래 총합
            System.out.println("판매내역의 총합입니다.");
            System.out.println();
            ArrayList<String[]> select = new ArrayList<>();
            select = resistDAO.selectTotalSaleByProduct(con);
            if(select != null){
                for (String[] total : select){
                    System.out.println("물품 = " + total[0] + " , 총 판매량 = " + total[1] +" , 총 판매액 = "+ total[2]);
                }
            }else{
                System.out.println("조회할 판매내역이 존재하지 않습니다. ");
            }
        } else if(choice == 5) {
//        거래 제거
            System.out.println("제거할 판매내역의 번호를 입력해주세요. ");
            int select = sc.nextInt();

            int delete = resistDAO.deleteSaleInfo(con, select);

            if(delete>0){
                System.out.println("=====삭제 성공!=====");
            }else{
                System.out.println("!!!!!=삭제 실패=!!!!!");
            }

        }
    }

    public void managerControl(){
        ManagerDAO resistDAO = new ManagerDAO();
        Connection con = getConnection();
        UserDAO resistUserDAO = new UserDAO();
        System.out.println("=======매니저관리 창=======");
        System.out.println("1. 매니저등록  2. 매니저코드 변경");
        int choice = sc.nextInt();
        if(choice == 1){
            System.out.println("매니저에 등록을 위해 정보등록 먼저 진행합니다.");
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
            UserDTO user = new UserDTO(0, userName, userPhone, userEmail, userAddr);
            int temp = resistUserDAO.inputUserInfo(con, user);
            int newManagerNum = resistUserDAO.selectLastUserNo(con);
            System.out.println("매니저페이지에 들어오기 위한 매니저 코드를 입력해주세요.");
            sc.nextLine();
            String managerCode = sc.nextLine();
            ManagerDTO newManager = new ManagerDTO(newManagerNum, managerCode);
            int result = resistDAO.inputManager(con, newManager);
            if(result>0){
                System.out.println("===등록 성공===");
            }else{
                System.out.println("!!!등록 실패!!!");
            }

        }else if(choice == 2){
            System.out.println("코드를 변경할 매니저의 번호를 입력하세요. ");
            int managerNo = sc.nextInt();
            System.out.println("변경할 코드를 입력해주세요. ");
            String managerCode = sc.nextLine();
            int result = resistDAO.updateManagerCode(con, managerNo, managerCode);
            if(result>0){
                System.out.println("===변경 성공===");
            }else{
                System.out.println("!!!변경 실패!!!");
            }
        }
    }
}
