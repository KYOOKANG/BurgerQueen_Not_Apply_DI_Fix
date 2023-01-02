package app.play;

import app.product.Product;
import app.product.ProductRepository;

import java.util.Scanner;

public class OrderApp {
    public void start() {
//        while (true) {
//            메뉴 출력
//            사용자 입력 받기
//
//            if (사용자 입력이 +인 경우) {
//                주문 내역 출력
//                break;
//            }
//            else if (사용자 입력이 0인 경우) {
//                장바구니 출력
//            }
//            else if (사용자 입력이 1부터 메뉴 마지막 번호에 해당하는 경우) {
//                사용자가 고른 상품의 옵션을 보여주고 고르게 한 후, 장바구니에 담기
//            }
//        }
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
