package app.play;

import app.product.Product;
import app.product.ProductRepository;
import app.product.subproduct.BurgerSet;
import app.product.subproduct.Drink;
import app.product.subproduct.Hamburger;
import app.product.subproduct.Side;

import java.util.Arrays;
import java.util.Scanner;

import static app.product.ProductRepository.NEW_PRODUCT_ID;

public class Cart {
    private Scanner scan = new Scanner(System.in);
    private Product[] items = new Product[0];
    private ProductRepository productRepository;
    private Menu menu;

    public Cart(ProductRepository productRepository, Menu menu) {
        this.productRepository = productRepository;
        this.menu = menu;
    }


    public void printCart() {
        System.out.println("🛒 장바구니");
        System.out.println("-".repeat(60));

        printCartItemDetails();

        int totalPrice = calculateTotalPrice();

        System.out.println("-".repeat(60));
        System.out.printf("합계 : %d원\n", totalPrice);

        System.out.println("이전으로 돌아가려면 엔터를 누르세요. ");
        scan.nextLine();
    }

    private void printCartItemDetails() {
        for(Product item: items) {
            if(item instanceof Hamburger) {
                System.out.printf("  %-8s %6d원 (단품)\n",
                        item.getName(),
                        item.getPrice()
                );
            }
            else if(item instanceof Side) {
                System.out.printf("  %-8s %6d원 (케첩 %d개)\n",
                        item.getName(),
                        item.getPrice(),
                        ((Side) item).getKetchup()
                );
            }
            else if(item instanceof Drink) {
                System.out.printf("  %-8s %6d원 (빨대 %s)\n",
                        item.getName(),
                        item.getPrice(),
                        ((Drink) item).hasStraw() ? "있음" : "없음"
                );
            }
            else {
                BurgerSet burgerSet = (BurgerSet) item;
                System.out.printf("  %s %6d원 (%s(케첩 %d개), %s(빨대 %s))\n",
                        burgerSet.getName(),
                        burgerSet.getPrice(),
                        burgerSet.getSide().getName(),
                        burgerSet.getSide().getKetchup(),
                        burgerSet.getDrink().getName(),
                        burgerSet.getDrink().hasStraw() ? "있음" : "없음"
                );
            }
        }
    }

    private int calculateTotalPrice() {
        int result = 0;
        for(Product item: items) {
            result += item.getPrice();
        }
        return result;
    }

    private void chooseOption(Product product) {

        String input;

        if (product instanceof Hamburger) {
            Hamburger h = (Hamburger) product;
            System.out.printf(
                    "단품으로 주문하시겠어요? (1)_단품(%d원) (2)_세트(%d원)\n",
                    h.getPrice(), h.getBurgerSetPrice()
            );
            input = scan.nextLine();
            h.setIsBurgerSet(input.equals("2"));
        }

	else if (product instanceof Side) {
            System.out.println("케첩은 몇개가 필요하신가요?");
            input = scan.nextLine();
            ((Side) product).setKetchup(Integer.parseInt(input));
        }

	else if (product instanceof Drink) {
            System.out.println("빨대가 필요하신가요? (1)_예 (2)_아니오");
            input = scan.nextLine();
            ((Drink) product).setHasStraw(input.equals("1"));
        }
    }

    public void addToCart(int productId) {

        Product product = productRepository.findById(productId);
        Product newProduct;

        chooseOption(product);

        // 햄버거 세트를 골랐다면 세트 메뉴를 구성해야함
        if (product instanceof Hamburger) {
            Hamburger h = (Hamburger) product;
            if(h.isBurgerSet()) product = composeSet(h);
        }

        if (product instanceof Hamburger)   newProduct = new Hamburger((Hamburger) product);
        else if (product instanceof Side)   newProduct = new Side((Side) product);
        else if (product instanceof Drink)  newProduct = new Drink((Drink) product);
        else                                newProduct = (BurgerSet) product;

        items = Arrays.copyOf(items, items.length+1);
        items[items.length-1] = newProduct;

        System.out.printf("[📣] %s를(을) 장바구니에 담았습니다.\n", product);
    }

    private BurgerSet composeSet(Hamburger hamburger) {
        System.out.println("사이드를 골라주세요");
        menu.printSides(false);

        int sideId = Integer.parseInt(scan.nextLine());
        Side side = (Side) productRepository.findById(sideId);
        chooseOption(side);


        System.out.println("음료를 골라주세요.");
        menu.printDrinks(false);

        int drinkId = Integer.parseInt(scan.nextLine());
        Drink drink = (Drink) productRepository.findById(drinkId);
        chooseOption(drink);


        String name = hamburger + "세트";
        int price = hamburger.getBurgerSetPrice();
        int kcal = hamburger.getKcal() + side.getKcal() + drink.getKcal();

        return new BurgerSet(NEW_PRODUCT_ID, name, price, kcal, hamburger, side, drink);
    }
}
