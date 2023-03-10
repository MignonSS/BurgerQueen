package app;

import app.product.Product;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

public class Menu {
    Product[] products;

    public Menu(Product[] products) {
        this.products = products;
    }

    // OrderApp μμ μ¬μ©
    public void printMenu() {
        System.out.println("[π»] λ©λ΄");
        System.out.println("-".repeat(60));

        // κ° λ©λ΄ μΆλ ₯νλ λΆλΆμ κ°κ°μ λ©μλλ‘ λΆμ° (λ¦¬ν©ν λ§)
        // μ΄ν μΈνΈλ©λ΄ κ΅¬μ± μ κ°κ²©μ΄ ν¬ν¨λμ΄ μμ§ μμ λ©λ΄ μΆλ ₯μ μν΄ κ°κ²© μΆλ ₯ μ¬λΆμ λν λ§€κ°λ³μλ₯Ό μΆκ°νμ¬ μ νν  μ μλλ‘ ν¨
        printHamburgers(true);
        printSides(true);
        printDrinks(true);

        System.out.println();
        System.out.println("π (0) μ₯λ°κ΅¬λ");
        System.out.println("π¦ (+) μ£Όλ¬ΈνκΈ°");
        System.out.println("-".repeat(60));
        System.out.print("[π£] λ©λ΄λ₯Ό μ νν΄μ£ΌμΈμ : ");
    }

    private void printHamburgers(boolean printPrice) {
        System.out.println("π νλ²κ±°");
        for(Product product : products) {
            if(product instanceof Hamburger) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    // Cart μμ μ¬μ©ν΄μΌ νλ―λ‘ μ κ·Όμ μ΄μ protected λ‘ μ€μ 
    protected void printSides(boolean printPrice) {
        System.out.println("π μ¬μ΄λ");
        for(Product product : products) {
            if(product instanceof Side) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    // Cart μμ μ¬μ©ν΄μΌ νλ―λ‘ μ κ·Όμ μ΄μ protected λ‘ μ€μ 
    protected void printDrinks(boolean printPrice) {
        System.out.println("π₯€ μλ£");
        for(Product product : products) {
            if(product instanceof Drink) {
                printEachMenu(product, printPrice);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product product, boolean printPrice) {
        if(printPrice) {
            System.out.printf(
                    "   (%d) %s %5dKcal %5dμ\n",
                    product.getId(), product.getName(), product.getKcal(), product.getPrice()
            );
        } else {
            System.out.printf(
                    "   (%d) %s %5dKcal\n",
                    product.getId(), product.getName(), product.getKcal()
            );
        }
    }

}
