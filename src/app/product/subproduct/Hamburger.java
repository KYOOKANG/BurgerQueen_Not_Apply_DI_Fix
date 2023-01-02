package app.product.subproduct;

import app.product.Product;

import static app.product.ProductRepository.NEW_PRODUCT_ID;


public class Hamburger extends Product {
    private boolean isBurgerSet;
    private int burgerSetPrice;

    public Hamburger(int id, String name, int price, int kcal, int burgerSetPrice) {
        this(id, name, price, kcal, false, burgerSetPrice);
    }
    public Hamburger(int id, String name, int price, int kcal, boolean isBurgerSet, int burgerSetPrice) {
        super(id, name, price, kcal);
        this.isBurgerSet = isBurgerSet;
        this.burgerSetPrice = burgerSetPrice;
    }
    public Hamburger(Hamburger hamburger) {
        this(
                NEW_PRODUCT_ID,
                hamburger.getName(),
                hamburger.getPrice(),
                hamburger.getKcal(),
                hamburger.isBurgerSet(),
                hamburger.getBurgerSetPrice()
        );
    }

    public boolean isBurgerSet() {
        return isBurgerSet;
    }

    public void setIsBurgerSet(boolean burgerSet) {
        isBurgerSet = burgerSet;
    }

    public int getBurgerSetPrice() {
        return burgerSetPrice;
    }
}
