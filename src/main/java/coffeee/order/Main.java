package coffeee.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting Coffee Order Board application demonstration.");

        CoffeeOrderBoard board = new CoffeeOrderBoard();

        logger.info("Додавання нових замовлень на дошку.");
        board.add(new Order("Alen"));
        board.add(new Order("Yoda"));
        board.add(new Order("Obi-Wan"));
        board.add(new Order("John Snow"));
        board.add(new Order("Daenerys"));

        System.out.println("\n--- Поточна черга ---");
        board.draw();
        System.out.println("---------------------\n");

        System.out.println("--- Доставка наступного замовлення ---");
        Order delivered1 = board.deliver();
        if (delivered1 != null) {
            System.out.println("Доставлено: " + delivered1);
        }
        System.out.println("\n--- Черга після першої доставки ---");
        board.draw();
        System.out.println("----------------------------------\n");

        System.out.println("--- Доставка конкретного замовлення (за номером) ---");
        Order delivered2 = board.deliver(4);
        if (delivered2 != null) {
            System.out.println("Доставлено: " + delivered2);
        }
        System.out.println("\n--- Черга після доставки замовлення №4 ---");
        board.draw();
        System.out.println("------------------------------------------\n");

        System.out.println("--- Спроба доставити неіснуюче замовлення (№99) ---");
        board.deliver(99);
        System.out.println("\n--- Черга залишається тією ж ---");
        board.draw();
        System.out.println("--------------------------------\n");

        System.out.println("--- Доставка решти замовлень ---");
        board.deliver();
        board.deliver();
        board.deliver();

        System.out.println("\n--- Остаточний стан черги ---");
        board.draw();
        System.out.println("-------------------------\n");

        try {
            logger.info("Спроба створити замовлення з нульовою назвою для виклику винятку.");
            board.add(new Order(null));
        } catch (IllegalArgumentException e) {
            logger.error("Зловив очікуваний виняток: {}", e.getMessage(), e);
        }
    }
}