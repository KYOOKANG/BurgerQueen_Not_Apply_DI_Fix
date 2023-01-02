package app.play;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Scanner;

public class Menu {
    Product[] products;
    Scanner scan = new Scanner(System.in);

    public Menu(Product[] products) {
        this.products = products;
    }

    public void printMenu() {
        System.out.println("[🔻] 메뉴");
        System.out.println("-".repeat(60));

        printHamburgers(true);
        printSides(true);
        printDrinks(true);

        System.out.println();
        System.out.println("🧺 (0) 장바구니");
        System.out.println("📦 (+) 주문하기");
        System.out.println("-".repeat(60));
        System.out.print("[📣] 메뉴를 선택해주세요 : ");
    }

    private void printDrinks(boolean isShow) {
        System.out.println("🥤 음료");
        for(Product p: products) {
            if(p instanceof Drink) {
                printEachMenu(p, isShow);
            }
        }
        System.out.println();
    }

    private void printSides(boolean isShow) {
        System.out.println("🍟 사이드");
        for(Product p: products) {
            if(p instanceof Side) {
                printEachMenu(p, isShow);
            }
        }
        System.out.println();
    }

    private void printHamburgers(boolean isShow) {
        System.out.println("🍔 햄버거");
        for(Product p: products) {
            if(p instanceof Hamburger) {
                printEachMenu(p, isShow);
            }
        }
        System.out.println();
    }

    private static void printEachMenu(Product p,boolean isShow) {
        System.out.printf(
                "   (%d) %s %5dKcal",
                p.getId(), p.getName(), p.getKcal()
        );
        if(isShow) System.out.printf(" %5d원", p.getPrice());

        System.out.println();
    }

}
