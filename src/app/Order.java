package app;


import app.discount.Discount;

import java.util.Scanner;

public class Order {
    // 1. 할인 적용
    // 2. 주문 상품(장바구니 내역) 출력
    // 3. 총 합계 금액과 할인 적용 금액 출력
    Scanner scanner = new Scanner(System.in);
    Cart cart;
    Discount discount;
    public Order(Cart cart, Discount discount) {
        this.cart = cart;
        this.discount = discount;
    }

    public void makeOrder() {

        int totalPrice = cart.calculateTotalPrice();
        int discountedPrice = discount.discount(totalPrice); // 할인 금액 적용

        System.out.println("[📣] 주문이 완료되었습니다.");
        System.out.println("[📣] 주문 내역은 다음과 같습니다.");
        System.out.println("-".repeat(60));

        cart.printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("금액 합계     : %d원\n", totalPrice);
        System.out.printf("할인 적용 금액 : %d원\n", discountedPrice);
    }
}
