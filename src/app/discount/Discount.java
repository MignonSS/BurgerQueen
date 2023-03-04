package app.discount;

import app.discount.discountCondition.DiscountCondition;

public class Discount {
    // 각 할인 조건을 모두 체크하여 조건에 맞는 경우 할인 적용하는 역할.

    // DiscountCondition 의 배열을 필드 멤버로 가진다. (할인 조건 중첩 적용 가능)
    // 각각의 할인 조건을 배열로 다루기 위해 하나의 인터페이스 DiscountCondition 을 구현하도록 한 것.

    DiscountCondition[] discountConditions;

    public Discount(DiscountCondition[] discountConditions) {
        this.discountConditions = discountConditions;
    }

    // 각 할인 조건을 모두 체크하여 조건에 맞는 경우 할인 적용 후 최종 금액을 반환
    // Order 에서 사용된다.
    public int discount(int price) {

        int discountedPrice = price;

        for(DiscountCondition discountCondition : discountConditions) {
            discountCondition.checkDiscountcondition(); // 각 할인 조건 부합 여부 체크
            if(discountCondition.isSatisfied()) discountedPrice = discountCondition.applyDiscount(discountedPrice);
        }

        return discountedPrice;
    }

}
