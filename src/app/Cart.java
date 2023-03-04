package app;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Cart {

    Scanner scanner = new Scanner(System.in);

    // 사용자가 선택한 상품들을 담고 있을 배열. 상품이 추가될 때마다 길이를 1씩 늘린다.
    Product[] items = new Product[0];

    ProductRepository productRepository;
    Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }

    // 장바구니에 선택한 메뉴 담기 => OrderApp 에서 사용
    public void addToCart(int productId) {
        // 선택한 메뉴 정보 가져오기 (ProductRepository 에서)
        Product product = productRepository.findById(productId);

        // 각 메뉴에 대한 옵션 적용 필요 => chooseOption()
        chooseOption(product);

        // 햄버거 세트를 선택한 경우 세트 구성 필요 => composeSet()
        if(product instanceof Hamburger) {
            if(((Hamburger) product).isBurgerSet()) product = composeSet((Hamburger) product);
        }

        // 배열의 길이를 1 늘리고, 새로 선택한 product 를 마지막 요소로 추가
        Product[] newItems = new Product[items.length + 1];
        System.arraycopy(items, 0, newItems, 0, items.length);
        newItems[newItems.length - 1] = product;
        items = newItems;

        System.out.printf("[📣]%s를(을) 장바구니에 담았습니다.\n", product.getName());
    }

    private BurgerSet composeSet(Hamburger hamburger) {

        System.out.println("사이드를 골라주세요");
        menu.printSides(false);

        int sideId = Integer.parseInt(scanner.nextLine());
        Side side = (Side) productRepository.findById(sideId);
        chooseOption(side);

        System.out.println("음료를 골라주세요");
        menu.printDrinks(false);

        int drinkId = Integer.parseInt(scanner.nextLine());
        Drink drink = (Drink) productRepository.findById(drinkId);
        chooseOption(drink);

        String name = hamburger.getName() + "세트";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(name, price, kcal, hamburger, side, drink);
    }

    private void chooseOption(Product product) {
        String input;

        if(product instanceof Hamburger) {
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    product.getPrice(), ((Hamburger) product).getBurgerSetPrice()
            );
            input = scanner.nextLine();
            if(input.equals("2")) ((Hamburger) product).setBurgerSet(true);
        }
        else if(product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scanner.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }
        else if(product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scanner.nextLine();
            if(input.equals("2")) ((Drink) product).setHasStraw(false);
        }

    }


    // 장바구니 메뉴 출력 => OrderApp 에서 사용
    public void printCart() {
        // [상품 상세 정보 출력]과 [상품 금액 합계 계산]이 필요하다. => 메서드로 추가

        System.out.println("🛒 장바구니");
        System.out.println("-".repeat(60));

        // 상품 상세 정보
        printCartItemDetails();

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", calculateTotalPrice());
        System.out.print("이전으로 돌아가려면 엔터를 누르세요.");
        scanner.nextLine(); // 장바구니 정보를 출력한 후 추가적인 (아무) 입력이 있어야 다음으로 넘어가도록 입력 받는 코드 추가

    }

    private int calculateTotalPrice() {
        int totalPrice = 0;
        for(Product product : items) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    private void printCartItemDetails() {
        // 상품 종류에 따라 출력되는 옵션이 다르다.

        for(Product product : items) {

            if(product instanceof BurgerSet) {
                BurgerSet burgerSet = (BurgerSet) product;
                System.out.printf(
                        "  %s %7d원 (%s(케첩 %s개), %s(빨대 %s))\n",
                        product.getName(),
                        product.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음"
                );
            }
            else if(product instanceof Hamburger) {
                System.out.printf(
                        "  %-8s %6d원 (단품)\n",
                        product.getName(),
                        product.getPrice()
                );
            }
            else if(product instanceof Side) {
                System.out.printf(
                        "  %-8s %6d원 (케첩 %d개)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Side) product).getKetchup()
                );
            }
            else if(product instanceof Drink) {
                System.out.printf(
                        "  %-8s %6d원 (빨대 %s)\n",
                        product.getName(),
                        product.getPrice(),
                        ((Drink) product).hasStraw() ? "있음" : "없음"
                );
            }

        } // for 문 종료

    } // printCartItemDetails() 종료

}
