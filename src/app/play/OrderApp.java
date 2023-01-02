package app.play;

import app.product.*;

import java.util.Scanner;

public class OrderApp {
    public void start() {
        Scanner scan = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        Product[] products = productRepository.getAllProducts();
        Menu menu = new Menu(products);
        Cart cart = new Cart(productRepository, menu);
        Order order = new Order(cart);

        System.out.println("🍔 BurgerQueen Order Service");
        while (true) {
            menu.printMenu();
            String input = scan.nextLine();

            switch(input) {
                case "+":   order.makeOrder();  return;     // 주문내역 출력 후 종료
                case "0":   cart.printCart();   break;
                default:
                    int id = Integer.parseInt(input);
                    for(Product p: products) {
                        if(p.getId() == id)
                            cart.addToCart(id);
                    }
            }
        }
    }
}
