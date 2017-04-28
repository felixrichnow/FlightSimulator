import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * The CheckInSystem keeps track of all of
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

public class CheckInSystem {
    private Order testOrder;
    private sortedComparator sortComp = new sortedComparator();
    private ArrayList<listNodeOrderHolder> sortedOrders = new ArrayList<>();
    private ConcurrentSkipListSet<listNodeOrderHolder> myMap = new ConcurrentSkipListSet<>(new sortedComparator());

    /**
     * The constructor of CheckInSystem creates
     * all the possible combinations of routes
     * by using two ArrayLists of all destinations/departuers.
     * It then initiates a Node/OrderHolder for each possible route.
     * This is to avoid null-situations.
     */

    public CheckInSystem() {
        List<String> cities1 = new ArrayList<>(Arrays.asList("Stockholm", "London", "Rome", "Paris", "Berlin"));
        List<String> cities2 = new ArrayList<>(Arrays.asList("Stockholm", "London", "Rome", "Paris", "Berlin"));

        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                if (i != j) {
                    listNodeOrderHolder nodeAdd = new listNodeOrderHolder(cities1.get(i), cities2.get(j));
                    myMap.add(nodeAdd);
                }
            }
        }
    }

    /**
     * This method is used to sort all the orders
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @return sortedOrders This returns a list of the sorted Orders.
     */

    ArrayList<listNodeOrderHolder> sortByTheMostPrioritizedRoute() {
        sortedOrders = myMap.stream().sorted(sortComp).collect(Collectors.toCollection(ArrayList::new));
        //System.out.println("First sorted element : " +sortedOrders.get(0).getKeyString() +" "  +sortedOrders.get(0).getNumberOfPassengers());
        return sortedOrders;
    }

    /**
     * This method is used to print all the sorted orders.
     * It also calls the sort method in case it hasn't been called before.
     *
     * @param color Used to print the orders in different colors for each plane
     */

    void printSortedOrders(String color) {
        sortedOrders = this.sortByTheMostPrioritizedRoute();
        sortedOrders.forEach(e -> System.out.println(color + "SortedNode : " + e.getKeyString() + " " + e.getNumberOfPassengers()));
        //To make the print color go back to normal
        System.out.println("\u001B[0m");
    }

    /**
     * This method is used to add a new Order to the checkInSystem.
     * It uses an iterator to go through the nodes, find the right
     * one and add the order to it. Also it only adds one order at
     * a time so if the right Node is found, break is called.
     *
     * @param testOrder Order that's added to the system
     */
    synchronized void addOrderToTheCheckInSystem(Order testOrder) {
            Iterator<listNodeOrderHolder> testIterator = myMap.iterator();
            //System.out.println("Attempting to add " + testOrder.getDestination() + testOrder.getDeparture());
            while (testIterator.hasNext()) {
                listNodeOrderHolder currentNode = testIterator.next();
                if (currentNode.getKeyString().equalsIgnoreCase(testOrder.getDeparture()+testOrder.getDestination())) {
                    //System.out.println("Found! :" + testOrder.getDestination() + testOrder.getDeparture());
                    currentNode.addOrderToOrderArrayList(testOrder);
                    break;
                }

            }
        }


    /**
     * This method is used to remove an Order from the checkInSystem.
     * It uses an iterator to go through the nodes, find the right
     * one and remove the order from it. Also it only removes one order at
     * a time so if the right Node is found, break is called.
     *
     * @param testOrder Order that's added to the system
     */
    synchronized public void removeOrderFromTheCheckInSystem(Order testOrder) {
        Iterator<listNodeOrderHolder> testIterator = myMap.iterator();
        System.out.println("Attempting to remove " + testOrder.getDeparture() + testOrder.getDestination());
        while (testIterator.hasNext()) {
            listNodeOrderHolder currentNode = testIterator.next();
            if (currentNode.getKeyString().equalsIgnoreCase(testOrder.getDeparture() + testOrder.getDestination())) {
                                System.out.println("Found! :" + testOrder.getDeparture() + testOrder.getDestination());
                currentNode.removeOrderFromOrderArrayList(testOrder);
                break;
            }

        }
    }
}


