package app;

import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp { // 주문 프로그램 메인 로직
    Scanner scanner = new Scanner(System.in);
    ProductRepository productRepository = new ProductRepository();
    Menu menu = new Menu(productRepository.getAllProducts());
    Cart cart = new Cart(productRepository, menu);

    public void start() {

        System.out.println("🍔 BurgerQueen Order Service");


        while(true) {

            // 메뉴 출력 및 메뉴 입력 받기
            menu.printMenu();
            String input = scanner.nextLine();

            // 사용자의 입력이 + 라면
            if(input.equals("+")) {
                // 주문하기
                // - 할인 적용
                // - 주문 메뉴 및 금액 출력
                break;
            }

            else {
                int menuNumber = Integer.parseInt(input);

                // 사용자가 입력이 0 이라면
                if(menuNumber == 0) {
                    // 장바구니에 담긴 메뉴 출력
                    cart.printCart();
                }
                // 사용자가 입력이 상품 메뉴 번호일 때
                else if(1 <= menuNumber && menuNumber <= productRepository.getAllProducts().length) {
                    // 장바구니에 메뉴 담기
                    cart.addToCart(menuNumber);
                }

            }

        } //  while 문 종료

    } // start() 종료
}
