package app.discount.discountCondition;

import app.discount.discountPolicy.DiscountPolicy;

import java.util.Scanner;

public class KidDiscountCondition implements DiscountCondition {
    boolean isSatisfied;

    // 외부에서 주입된 DiscountPolicy 에 따라 할인 금액이 계산된다.
    DiscountPolicy discountPolicy;

    public KidDiscountCondition(DiscountPolicy discountPolicy) {
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
        System.out.println("나이가 어떻게 되세요?");
        int input = Integer.parseInt(scanner.nextLine());
        setSatisfied(input < 20);

    }

    @Override
    public int applyDiscount(int price) {
        return discountPolicy.calculateDiscountedPrice(price);
    }
}
