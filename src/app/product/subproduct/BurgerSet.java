package app.product.subproduct;

import app.product.Product;

public class BurgerSet extends Product {
    Hamburger hamburger;
    Side side;
    Drink drink;

    public BurgerSet(int id, String name, int price, int kcal, Hamburger hamburger, Side side, Drink drink) {
        super(id, name, price, kcal);
        this.hamburger = hamburger;
        this.side = side;
        this.drink = drink;
    }
}
