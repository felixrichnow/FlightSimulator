import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;


public class Mainclass {


    public static void main(String[] args) {
    CheckInSystem testCheckInSystem = new CheckInSystem();
    OrderGenerator testGenerator = new OrderGenerator();
        Order order1 = testGenerator.createANewOrder();
        Order order2 = testGenerator.createANewOrder();
        Order order3 = testGenerator.createANewOrder();
        Order order4 = testGenerator.createANewOrder();
        Order order5 = testGenerator.createANewOrder();
        Order order6 = testGenerator.createANewOrder();
        Order order7 = testGenerator.createANewOrder();
        testCheckInSystem.addOrderToTheCheckInSystem(order1);
        testCheckInSystem.addOrderToTheCheckInSystem(order2);
        testCheckInSystem.addOrderToTheCheckInSystem(order3);
        testCheckInSystem.addOrderToTheCheckInSystem(order4);
        testCheckInSystem.addOrderToTheCheckInSystem(order5);
        testCheckInSystem.addOrderToTheCheckInSystem(order6);
        testCheckInSystem.addOrderToTheCheckInSystem(order7);
        testCheckInSystem.sortByTheMostPrioritizedRoute();

    }
}