package app.discount.discountPolicy;

public class FixedRateDiscountPolicy {
    private int discountRate;
    final public static int FIXED_RATE = 10;

    public FixedRateDiscountPolicy(int discountRate) {
        this.discountRate = discountRate;
    }

    public int calculateDiscountedPrice(int price) {
        return price * (100 - discountRate)/100;
    }
}
