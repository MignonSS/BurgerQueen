package app.product.subproduct;

import app.product.Product;

public class BurgerSet extends Product {

    Hamburger hamburger;
    Side side;
    Drink drink;

    // 버거세트는 Repository 에 저장되는 메뉴가 아니라 사용자가 직접 구성하는 정보이기 때문에 id 가 필요하지 않다.
    // 상위 클래스 Product 의 생성자도 이에 맞게 id가 없는 생성자를 오버로딩해준다.
    public BurgerSet(String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink) {
        super(name, price, kcal);
        this.hamburger = hamburger;
        this.side = side;
        this.drink = drink;
    }

    // BurgerSet 의 필드멤버 hamburger, side, drink 의 값은 생성자로 결정해줄 것이므로 Setter 는 필요하지 않다.
    public Hamburger getHamburger() {
        return hamburger;
    }

    public Side getSide() {
        return side;
    }

    public Drink getDrink() {
        return drink;
    }
}
