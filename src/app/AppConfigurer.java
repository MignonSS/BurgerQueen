package app;

import app.discount.Discount;
import app.discount.discountCondition.CozDiscountCondition;
import app.discount.discountCondition.DiscountCondition;
import app.discount.discountCondition.KidDiscountCondition;
import app.discount.discountPolicy.FixedAmountDiscountPolicy;
import app.discount.discountPolicy.FixedRateDiscountPolicy;
import app.product.ProductRepository;

public class AppConfigurer {

    private ProductRepository productRepository = new ProductRepository();
    private Menu menu = new Menu(productRepository().getAllProducts());
    private Cart cart = new Cart(productRepository(), menu());

    public ProductRepository productRepository() {
        return productRepository;
    }
    public Menu menu() {
        return menu;
    }

    public Cart cart() {
        return cart;
    }

    public Discount discount() {
        return new Discount(
                new DiscountCondition[]{
                        new CozDiscountCondition(new FixedRateDiscountPolicy(10)),
                        new KidDiscountCondition(new FixedAmountDiscountPolicy(1000))
                }
        );
    }

    public Order order() {
        return new Order(cart(), discount());
    }
}
