package app.product.subproduct;

import app.product.Product;

public class Side extends Product {
    private int ketchup;

    public Side(int id, String name, int price, int kcal, int ketchup) {
        super(id, name, price, kcal);
        this.ketchup = ketchup;
    }

    // 깊은 복사를 위한 생성자
    // 동일 메뉴를 여러 번 장바구니에 담게 될 때 옵션이 개별 지정될 수 있도록 각각의 개별 인스턴스를 만드는 것.
    // 이미 사용자로부터 메뉴는 선택한 상태, 더이상 id는 필요하지 않다. id 빼고 복사.
    public Side(Side side) {
        super(side.getName(), side.getPrice(), side.getKcal());
        this.ketchup = side.getKetchup();
    }

    public int getKetchup() {
        return ketchup;
    }

    public void setKetchup(int ketchup) {
        this.ketchup = ketchup;
    }
}
