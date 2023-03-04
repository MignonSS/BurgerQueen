package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class Menu {
    Product[] products;

    public Menu(Product[] products) {
        this.products = products;
    }

    // OrderApp 에서 사용
    public void printMenu() {
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(60));

        // 각 메뉴 출력하는 부분을 각각의 메서드로 분산 (리팩토링)
        // 이후 세트메뉴 구성 시 가격이 포함되어 있지 않은 메뉴 출력을 위해 가격 출력 여부에 대한 매개변수를 추가하여 선택할 수 있도록 함
        printHamburgers(true);
        printSides(true);
        printDrinks(true);

        System.out.println();
        System.out.println("🛒 (0) 장바구니");
        System.out.println("📦 (+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.print("[📣] 메뉴를 선택해주세요 : ");
    }

    private void printHamburgers(boolean printPrice) {
        System.out.println("🍔 햄버거");
        for(Product product : products) {
            if(product instanceof Hamburger) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    // Cart 에서 사용해야 하므로 접근제어자 protected 로 설정
    protected void printSides(boolean printPrice) {
        System.out.println("🍟 사이드");
        for(Product product : products) {
            if(product instanceof Side) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    // Cart 에서 사용해야 하므로 접근제어자 protected 로 설정
    protected void printDrinks(boolean printPrice) {
        System.out.println("🥤 음료");
        for(Product product : products) {
            if(product instanceof Drink) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean printPrice) {
        if(printPrice) {
            System.out.printf(
                    "   (%d) %s %5dKcal %5d원\n",
                    product.getId(), product.getName(), product.getKcal(), product.getPrice()
            );
        } else {
            System.out.printf(
                    "   (%d) %s %5dKcal\n",
                    product.getId(), product.getName(), product.getKcal()
            );
        }
    }

}
