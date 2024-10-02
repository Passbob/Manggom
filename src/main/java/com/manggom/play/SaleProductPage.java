package com.manggom.play;

import com.manggom.model.dao.ProductDAO;
import com.manggom.model.dto.ProductDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import static com.manggom.common.JDBCTemplate.getConnection;

public class SaleProductPage {
    Scanner sc = new Scanner(System.in);
    ProductDAO resistDAO = new ProductDAO();

    public void startPurchase() {

        SaleProductPage sale = new SaleProductPage();
//        페이지 시작

        for (int i = 0; true; i++) {
            int choice;
                choice = sale.productSale();
                if(choice == 9){
                    break;
                }
        }
    }

    public int productSale(){
        Connection con = getConnection();
        InformationPage info = new InformationPage();
        ArrayList<ProductDTO> productList = new ArrayList<>();
        productList = resistDAO.selectAllProduct(con);
        int[] count = new int[productList.size()];
        int[] price = new int[productList.size()];
        System.out.println("==============망곰이 굿즈===============");
        for(int i=0; i < productList.size(); i++){
            String condition = "";
            count[i] = productList.get(i).getProductCount();
            price[i] = productList.get(i).getProductPrice();
            if(count[i] > 0){
                condition = "구매가능";
            }else {
                condition = "품절";
            }
            System.out.println(productList.get(i)+ "  " + condition);
        }
        System.out.println(" 9. 나가기 ");
        System.out.println("======================================");
        System.out.println("원하시는 번호를 입력해주세요. ");
        int choiceProduct = sc.nextInt();
        if(choiceProduct == 41630989){
            ManagerPage manager = new ManagerPage();
            manager.managerPassword();
        }else {
            System.out.println("구매하실 수량을 선택해주세요. ");
            int choiceProductCount = sc.nextInt();
            System.out.println();
            int choiceProductPrice = price[choiceProduct-1];
            int totalPrice = choiceProductPrice*choiceProductCount;
            int restCount = count[choiceProduct-1];


            if (choiceProductCount > 5) {
                System.out.println("한 번에 5개까지만 구매 가능합니다. ");
            } else if (restCount - choiceProductCount >= 0) {
                System.out.println("구매하신 총 가격은 " + (totalPrice) + "원 입니다. ");
                choiceProduct = info.startInformation(choiceProduct, choiceProductCount, totalPrice, restCount);
            } else {
                System.out.println("요청하신 수량이 재고의 수량을 초과합니다.");
            }
        }

        return choiceProduct;
    }


}
