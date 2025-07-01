package coffeee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private int orderNumber;
    private final String customerName;

    public Order(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            logger.error("Спроба створити замовлення з порожнім або невказаним ім'ям клієнта.");
            throw new IllegalArgumentException("Ім'я клієнта не може бути порожнім або не має значення null.");
        }
        this.customerName = customerName;
        logger.info("Замовлення створено для клієнта '{}'.", customerName);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
        logger.debug("Клієнту призначено номер замовлення {}'{}'.", orderNumber, customerName);
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return String.format("%-5d | %s", orderNumber, customerName);
    }
}