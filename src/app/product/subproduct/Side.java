package app.product.subproduct;

import app.product.Product;

import static app.product.ProductRepository.NEW_PRODUCT_ID;

public class Side extends Product {
    private int ketchup;

    public Side(int id, String name, int price, int kcal) {
        this(id, name, price, kcal, 1);
    }
    public Side(int id, String name, int price, int kcal, int ketchup) {
        super(id, name, price, kcal);
        this.ketchup = ketchup;
    }
    public Side(Side side) {
        this(
                NEW_PRODUCT_ID,
                side.getName(),
                side.getPrice(),
                side.getKcal(),
                side.getKetchup()
        );
    }

    public int getKetchup() {
        return ketchup;
    }

    public void setKetchup(int ketchup) {
        this.ketchup = ketchup;
    }
}
