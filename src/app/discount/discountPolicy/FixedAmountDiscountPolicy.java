package app.discount.discountPolicy;

public class FixedAmountDiscountPolicy {
    private int discountAmount;
    final public static int FIXED_AMOUNT = 500;

    public FixedAmountDiscountPolicy(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int calculateDiscountedPrice(int price) {
        return price - discountAmount;
    }
}
