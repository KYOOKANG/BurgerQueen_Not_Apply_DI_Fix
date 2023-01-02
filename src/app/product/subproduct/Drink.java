package app.product.subproduct;

import app.product.Product;

import static app.product.ProductRepository.NEW_PRODUCT_ID;

public class Drink extends Product {
    private boolean hasStraw;

    public Drink(int id, String name, int price, int kcal) {
        this(id, name, price, kcal, true);
    }
    public Drink(int id, String name, int price, int kcal, boolean hasStraw) {
        super(id, name, price, kcal);
        this.hasStraw = hasStraw;
    }
    public Drink(Drink drink) {
        this(
                NEW_PRODUCT_ID,
                drink.getName(),
                drink.getPrice(),
                drink.getKcal(),
                drink.hasStraw()
        );
    }

    public boolean hasStraw() {
        return hasStraw;
    }

    public void setHasStraw(boolean hasStraw) {
        this.hasStraw = hasStraw;
    }
}
