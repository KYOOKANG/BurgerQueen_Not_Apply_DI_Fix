package app.discount.discountCondition;

import app.discount.discountPolicy.FixedAmountDiscountPolicy;

import java.util.Scanner;

import static app.discount.discountPolicy.FixedAmountDiscountPolicy.FIXED_AMOUNT;

public class KidDiscountCondition {
    private boolean isSatisfied;
    final private static int CUT_LINE_AGE = 20;

    private FixedAmountDiscountPolicy fixedAmountDiscountPolicy = new FixedAmountDiscountPolicy(FIXED_AMOUNT);

    public boolean isSatisfied() {
        return isSatisfied;
    }

    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

    public void checkDiscountCondition() {
        Scanner scan = new Scanner(System.in);

        System.out.println("나이가 어떻게 되십니까?");
        int input = Integer.parseInt(scan.nextLine());

        setSatisfied(input < CUT_LINE_AGE);
    }

    public int applyDiscount(int price) {
        return fixedAmountDiscountPolicy.calculateDiscountedPrice(price);
    }
}
