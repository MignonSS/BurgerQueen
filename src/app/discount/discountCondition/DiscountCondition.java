package app.discount.discountCondition;

public interface DiscountCondition {
    void checkDiscountcondition();
    int applyDiscount(int price);
    boolean isSatisfied();
}
