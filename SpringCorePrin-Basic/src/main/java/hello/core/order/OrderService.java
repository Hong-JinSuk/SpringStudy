package hello.core.order;

public interface OrderService { // return 주문결과
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
