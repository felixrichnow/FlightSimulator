import java.util.ArrayList;


/**
 * Created by user on 2017-04-07.
 */

public class treeMapNodeOrderHolder implements Comparable<treeMapNodeOrderHolder> {

    private String keyString;
    private String currentDepature;
    private String currentDestination;
    private int numberOfPassengers;
    private ArrayList<Order> orderHolderArrayList;

    public treeMapNodeOrderHolder(Order testOrder) {
        //Create a new node using an Order
        this.keyString = testOrder.getDeparture() + testOrder.getDeparture();
        this.currentDepature = testOrder.getDeparture();
        this.currentDestination = testOrder.getDestination();
        this.orderHolderArrayList = new ArrayList<Order>();
    }

    public treeMapNodeOrderHolder(String departure, String destination) {
        //Create a new node using Strings (this is for initialization of map)
        this.keyString = departure + destination;
        this.currentDepature = departure;
        this.currentDestination = destination;
        orderHolderArrayList = new ArrayList<Order>();
    }

    @Override
    public int compareTo(treeMapNodeOrderHolder that) {
        return this.keyString.compareTo(that.keyString);
    }

    public ArrayList<Order> getOrderArrayList() {
        return orderHolderArrayList;
    }

    public void addOrderToOrderArrayList(Order ordertoAdd) {
        numberOfPassengers += ordertoAdd.getPassengers();
        orderHolderArrayList.add(ordertoAdd);
    }


    public void removeOrderFromOrderArrayList(Order ordertoRemove) {
        numberOfPassengers -= ordertoRemove.getPassengers();
        orderHolderArrayList.remove(ordertoRemove);
    }

    public String getKeyString() {
        return keyString;
    }

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    @Override
    public String toString() {
        return keyString;
    }

    public String getCurrentDeparture() {
        return currentDepature;
    }

    public String getCurrentDestination() {
        return currentDestination;
    }

}
