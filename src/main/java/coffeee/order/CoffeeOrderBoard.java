package coffeee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {
    private static final Logger logger = LoggerFactory.getLogger(CoffeeOrderBoard.class);
    private final Queue<Order> orders;
    private int nextOrderNumber;

    public CoffeeOrderBoard() {
        this.orders = new LinkedList<>();
        this.nextOrderNumber = 1;
        logger.info("CoffeeOrderBoard ініціалізовано. Готово приймати замовлення..");
    }

    public void add(Order order) {
        try {
            logger.debug("Спроба додати нове замовлення для клієнта '{}'.", order.getCustomerName());
            order.setOrderNumber(nextOrderNumber);
            orders.add(order);
            logger.info("Замовлення для '{}' додано до черги з номером {}.", order.getCustomerName(), nextOrderNumber);
            nextOrderNumber++;
        } catch (Exception e) {
            logger.error("Не вдалося додати замовлення для клієнта '{}'. Виняток: {}", order.getCustomerName(), e.getMessage(), e);
        }
    }

    public Order deliver() {
        try {
            logger.debug("Спроба доставити наступне замовлення в черзі.");
            Order deliveredOrder = orders.poll();
            if (deliveredOrder != null) {
                logger.info("Номер замовлення доставлено {}. Клієнт: {}.", deliveredOrder.getOrderNumber(), deliveredOrder.getCustomerName());
                return deliveredOrder;
            } else {
                logger.warn("Спробували доставити замовлення, але черга порожня.");
                return null;
            }
        } catch (Exception e) {
            logger.error("Під час доставки наступного замовлення сталася неочікувана помилка. Виняток: {}", e.getMessage(), e);
            return null;
        }
    }

    public Order deliver(int orderNumber) {
        logger.debug("Спроба доставити замовлення з номером {}.", orderNumber);
        try {

            boolean orderFound = false;
            Order deliveredOrder = null;
            var iterator = orders.iterator();
            while (iterator.hasNext()) {
                Order currentOrder = iterator.next();
                if (currentOrder.getOrderNumber() == orderNumber) {
                    deliveredOrder = currentOrder;
                    iterator.remove();
                    orderFound = true;
                    logger.info("Номер замовлення, що доставлено: {}. Клієнт: {}.", deliveredOrder.getOrderNumber(), deliveredOrder.getCustomerName());
                    break;
                }
            }

            if (!orderFound) {
                logger.warn("Не вдалося доставити замовлення {}. Замовлення не знайдено в черзі.", orderNumber);
            }
            return deliveredOrder;
        } catch (Exception e) {
            logger.error("Під час доставки замовлення {} сталася неочікувана помилка. Виняток: {}", orderNumber, e.getMessage(), e);
            return null;
        }
    }

    public void draw() {
        logger.info("Малювання поточного стану дошки замовлень.");
        if (orders.isEmpty()) {
            logger.info("Дошка замовлень наразі порожня.");
            System.out.println("Num | Name");
            System.out.println("Черга порожня.");
            return;
        }

        System.out.println("Num | Name");
        System.out.println("--- | ----");
        for (Order order : orders) {
            System.out.println(order);
        }
        logger.info("\n" +
                "Відображено панель замовлень. Загальна кількість замовлень у черзі: {} .", orders.size());
    }
}