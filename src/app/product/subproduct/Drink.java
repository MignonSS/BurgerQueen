package app.product.subproduct;

import app.product.Product;

public class Drink extends Product {
    boolean hasStraw;

    public Drink(int id, String name, int price, int kcal, boolean hasStraw) {
        super(id, name, price, kcal);
        this.hasStraw = hasStraw;
    }

    // 깊은 복사를 위한 생성자
    // 동일 메뉴를 여러 번 장바구니에 담게 될 때 옵션이 개별 지정될 수 있도록 각각의 개별 인스턴스를 만드는 것.
    // 이미 사용자로부터 메뉴는 선택한 상태, 더이상 id는 필요하지 않다. id 빼고 복사.
    public Drink(Drink drink) {
        super(drink.getName(), drink.getPrice(), drink.getKcal());
        this.hasStraw = drink.hasStraw();
    }

    public boolean hasStraw() { // getter 이름 isHasStraw -> hasStraw 로 변경 (좀 더 직관적)
        return hasStraw;
    }

    public void setHasStraw(boolean hasStraw) {
        this.hasStraw = hasStraw;
    }
}
