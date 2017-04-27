import java.util.Random;

/**
 * Created by Felix Richnau on 2017-03-27.
 */
public class OrderGenerator extends Thread {
    String One = "Stockholm";
    String Two = "London";
    String Three = "Paris";
    String Four = "Berlin";
    String Five = "Rome";
    CheckInSystem orderSystem;

    public OrderGenerator (CheckInSystem orderSystem){
    this.orderSystem = orderSystem;
    }
    public OrderGenerator(){    }
    // Test
    Order createANewOrder(){
        Random randomGenerator = new Random();
        int randomPassenger = randomGenerator.nextInt(5) + 1;
        int randomDepartureNumber = randomGenerator.nextInt(5) + 1;
        int randomDestinationNumber = randomGenerator.nextInt(5) + 1;
        while(randomDepartureNumber == randomDestinationNumber){
            randomDestinationNumber = randomGenerator.nextInt(5) + 1;
        }
        String randomDeparture = figureOutDepartureAndDestination(randomDepartureNumber);
        String randomDestination = figureOutDepartureAndDestination(randomDestinationNumber);
        int randomTicketClass = randomGenerator.nextInt(3) + 1;
        return new Order(randomPassenger,randomDeparture,randomDestination,randomTicketClass);
    }

    public String figureOutDepartureAndDestination(int numberToBeTranslated){
        String departureString = new String();
        if(numberToBeTranslated == 1){
            departureString = One; // Stockholm
        }
        else if(numberToBeTranslated == 2){
            departureString = Two; // London
        }
        else if(numberToBeTranslated == 3){
            departureString = Three; // Paris
        }
        else if(numberToBeTranslated == 4){
            departureString = Four; // Berlin
        }
        else if(numberToBeTranslated == 5){
            departureString = Five; // Rome
        }
        return departureString; // translated the number into a string
    }

    @Override
    public void run(){
        boolean running = true;
        while(running = true){
            Order currentOrder = this.createANewOrder();
            System.out.println("New order : Dep" +currentOrder.getDeparture() +" Des: " +currentOrder.getDestination()
                            + " Pas: " +currentOrder.getPassengers() +" TicketClass " +currentOrder.getTicketClass());
            orderSystem.addOrderToTheCheckInSystem(currentOrder);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
