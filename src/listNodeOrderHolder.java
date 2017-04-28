import java.util.ArrayList;

/**
 * The treeMapNodeOrderHolder keeps track of all of
 * the Orders that are generated. It handles
 * them and can be taken and put onto airplanes
 * by airplanes. It uses a ConcurrentSkipList
 * to store all the orders and an ArrayList
 * for a sorted version of them. There's
 * also a custom made Comparator which
 * sorts the Orders by their amount
 * of passengers.
 *
 * @author Felix Richnau
 * @version 1.0
 * @since 2017-04-27
 */
public class listNodeOrderHolder
//implements Comparable<listNodeOrderHolder>
{

    private String keyString;
    private String currentDeparture;
    private String currentDestination;
    private int numberOfPassengers;
    private ArrayList<Order> orderHolderArrayList;

    /**
     * Constructor for initializing nodes in the CheckInSystem.
     * @param departure,destiantion (required) to initialize new node
     */
    public listNodeOrderHolder(String departure, String destination) {
        //Create a new node using Strings (this is for initialization of map)
        this.keyString = departure + destination;
        this.currentDeparture = departure;
        this.currentDestination = destination;
        orderHolderArrayList = new ArrayList<>();
    }

    /**
     * Constructor for special case of new nodes. Used for testing.
     * @param testOrder (required) for info for a new Node
     */
    public listNodeOrderHolder(Order testOrder) {
        //Create a new node using an Order
        this.keyString = testOrder.getDeparture() + testOrder.getDeparture();
        this.currentDeparture = testOrder.getDeparture();
        this.currentDestination = testOrder.getDestination();
        this.orderHolderArrayList = new ArrayList<>();
    }

    /**
     * Method addOrderToOrderArrayList.
     * This method is used to add an Order to this listNodeOrderHolder.
     * It adds the order to an orderHolderArrayList inside of it.
     *
     * @param ordertoAdd Order that's added to the Node
     */
    void addOrderToOrderArrayList(Order ordertoAdd) {
        numberOfPassengers += ordertoAdd.getPassengers();
        orderHolderArrayList.add(ordertoAdd);
    }

    /**
     * Method removeOrderToOrderArrayList
     * This method is used to remove an Order to this listNodeOrderHolder.
     * It removes the order from an orderHolderArrayList inside of it.
     *
     * @param ordertoRemove Order that's removed from the Node
     */
    void removeOrderFromOrderArrayList(Order ordertoRemove) {
        numberOfPassengers -= ordertoRemove.getPassengers();
        orderHolderArrayList.remove(ordertoRemove);
    }

    // Help methods, Getters //

    /**
     * Method getKeyString
     * This method is used to get access to the Nodes keyString.
     * @return keyString the node's keyString (String)
     */
    String getKeyString() {
        return keyString;
    }

    /**
     * Method getNumberOfPassengers
     * This method is used to get access to the Nodes numberOfPassengers.
     * @return numberOfPassengers the node's amount of passengers
     */
    int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * Method getKeyString
     * This method is used to get access to the Nodes keyString.
     * @return keyString the node's keyString (String)
     */
    ArrayList<Order> getOrderArrayList() {
        return orderHolderArrayList;
    }

    /**
     * Method getCurrentDeparture
     * This method is used to get access to the Nodes Departure city.
     * @return currentDeparture the node's current Departure (String)
     */
    String getCurrentDeparture() {
        return currentDeparture;
    }

    /**
     * Method getCurrentDestination
     * This method is used to get access to the Nodes Destination city.
     * @return currentDeparture the node's current Destination (String)
     */
    String getCurrentDestination() {
        return currentDestination;
    }

    /**
     * Method toString implemented/Overridden
     * This method can be used to test and find out current Node's name in form of String.
     * @return keyString the Node as a String will be its keyString (which should be unique)
     */
    @Override
    public String toString() {
        return keyString;
    }

}
