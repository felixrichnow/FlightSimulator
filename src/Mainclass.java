import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class Mainclass {


    public static void main(String[] args) {
    CheckInSystem testCheckInSystem = new CheckInSystem();
    OrderGenerator testGenerator = new OrderGenerator();
        Order order1 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order1.getDeparture()+order1.getDestination()
                +" Pas " +order1.getPassengers());
        Order order2 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order2.getDeparture()+order2.getDestination()
                +" Pas " +order2.getPassengers());
        Order order3 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order3.getDeparture()+order3.getDestination()
                +" Pas " +order3.getPassengers());
        Order order4 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order4.getDeparture()+order4.getDestination()
                +" Pas " +order4.getPassengers());
        Order order5 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order5.getDeparture()+order5.getDestination()
                +" Pas " +order5.getPassengers());
        Order order6 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order6.getDeparture()+order6.getDestination()
                +" Pas " +order6.getPassengers());
        Order order7 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order7.getDeparture()+order7.getDestination()
                +" Pas " +order7.getPassengers());
        testCheckInSystem.addOrderToTheCheckInSystem(order1);
        testCheckInSystem.addOrderToTheCheckInSystem(order2);
        testCheckInSystem.addOrderToTheCheckInSystem(order3);
        testCheckInSystem.addOrderToTheCheckInSystem(order4);
        testCheckInSystem.addOrderToTheCheckInSystem(order5);
        testCheckInSystem.addOrderToTheCheckInSystem(order6);
        testCheckInSystem.addOrderToTheCheckInSystem(order7);
        Order order11 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order11.getDeparture()+order11.getDestination()
                +" Pas " +order11.getPassengers());
        Order order22 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order22.getDeparture()+order22.getDestination()
                +" Pas " +order22.getPassengers());
        Order order33 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order33.getDeparture()+order33.getDestination()
                +" Pas " +order33.getPassengers());
        Order order44 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order44.getDeparture()+order44.getDestination()
                +" Pas " +order44.getPassengers());
        Order order55 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order55.getDeparture()+order55.getDestination()
                +" Pas " +order55.getPassengers());
        Order order66 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order66.getDeparture()+order66.getDestination()
                +" Pas " +order66.getPassengers());
        Order order77 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order77.getDeparture()+order77.getDestination()
                +" Pas " +order77.getPassengers());
        testCheckInSystem.addOrderToTheCheckInSystem(order11);
        testCheckInSystem.addOrderToTheCheckInSystem(order22);
        testCheckInSystem.addOrderToTheCheckInSystem(order33);
        testCheckInSystem.addOrderToTheCheckInSystem(order44);
        testCheckInSystem.addOrderToTheCheckInSystem(order55);
        testCheckInSystem.addOrderToTheCheckInSystem(order66);
        testCheckInSystem.addOrderToTheCheckInSystem(order77);
        Order order12 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order12.getDeparture()+order12.getDestination()
                +" Pas " +order12.getPassengers());
        Order order23 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order23.getDeparture()+order23.getDestination()
                +" Pas " +order23.getPassengers());
        Order order34 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order34.getDeparture()+order34.getDestination()
                +" Pas " +order34.getPassengers());
        Order order45 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order45.getDeparture()+order45.getDestination()
                +" Pas " +order45.getPassengers());
        Order order56 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order56.getDeparture()+order56.getDestination()
                +" Pas " +order56.getPassengers());
        Order order67 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order67.getDeparture()+order67.getDestination()
                +" Pas " +order67.getPassengers());
        Order order78 = testGenerator.createANewOrder();
        System.out.println("Order created : KeyString : " +order78.getDeparture()+order78.getDestination()
                +" Pas " +order78.getPassengers());
        testCheckInSystem.addOrderToTheCheckInSystem(order12);
        testCheckInSystem.addOrderToTheCheckInSystem(order23);
        testCheckInSystem.addOrderToTheCheckInSystem(order34);
        testCheckInSystem.addOrderToTheCheckInSystem(order45);
        testCheckInSystem.addOrderToTheCheckInSystem(order56);
        testCheckInSystem.addOrderToTheCheckInSystem(order67);
        testCheckInSystem.addOrderToTheCheckInSystem(order78);




        testCheckInSystem.sortByTheMostPrioritizedRoute();



        Airplane testAirPlane = new Airplane(testCheckInSystem);
        testAirPlane.setCurrentDistanceToDestination(20);
        testAirPlane.currentLocation = "London";
        testAirPlane.run();


    }
}