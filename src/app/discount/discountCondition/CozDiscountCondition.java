package app.discount.discountCondition;

import app.discount.discountPolicy.FixedRateDiscountPolicy;

import java.util.Scanner;

import static app.discount.discountPolicy.FixedRateDiscountPolicy.FIXED_RATE;

public class CozDiscountCondition {
    private boolean isSatisfied;

    private FixedRateDiscountPolicy fixedRateDiscountPolicy = new FixedRateDiscountPolicy(FIXED_RATE);

    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public void checkDiscountCondition() {
        Scanner scan = new Scanner(System.in);

        System.out.println("코드스테이츠 수강생이십니까? (1)_예 (2)_아니오");
        String input = scan.nextLine();

        setSatisfied(input.equals("1"));
    }

    public int applyDiscount(int price) {
        return fixedRateDiscountPolicy.calculateDiscountedPrice(price);
    }
}
