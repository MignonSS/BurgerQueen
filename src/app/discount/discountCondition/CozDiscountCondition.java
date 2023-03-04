package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class CozDiscountCondition implements DiscountCondition {
    boolean isSatisfied;

    // 외부에서 주입된 DiscountPolicy 에 따라 할인 금액이 계산된다.
    DiscountPolicy discountPolicy;

    public CozDiscountCondition(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }

    public void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    @Override
    public void checkDiscountcondition() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("코드스테이츠 수강생이십니까? (1)_예 (2)_아니오");
        String input = scanner.nextLine();

        if(input.equals("1")) setSatisfied(true);
        else setSatisfied(false);
    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }
}
