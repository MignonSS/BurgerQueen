package app.product;

import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class ProductRepository { // 개별 상품 정보를 담고 있는 클래스

    private final Product[] products = {
            new Hamburger(1, "새우버거", 3500, 500, false, 4500),
            new Hamburger(2, "치킨버거", 4000, 600, false, 5000),
            new Side(3, "감자튀김", 1000, 300, 1),
            new Side(4, "어니언링", 1000, 300, 1),
            new Drink(5, "코카콜라", 1000, 200, true),
            new Drink(6, "제로콜라", 1000, 0, true)
    };

    public Product[] getAllProducts() {
        return products;
    }

    public Product findById(int productId) {

        for(Product product : products) {
            if(product.getId() == productId) {
                // 사용자가 입력한 메뉴 넘버와 일치하는 아이디의 product 에 대해 깊은 복사본 인스턴스 반환
                // 이미 일치하는 상황이기 때문에 Hamburger, Side, Drink 중 어느 하나에는 무조건 해당한다.
                if(product instanceof Hamburger) return new Hamburger((Hamburger) product);
                else if(product instanceof Side) return new Side((Side) product);
                else if(product instanceof Drink) return new Drink((Drink) product);
            }
        }
        return null; // 일치하는 정보가 없을 때
    }

}
