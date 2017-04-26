import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;



/**
 * Created by Felix on 2017-03-27.
 * The CheckInSystem keeps track of all of
 * the AirPlanes it sorts and puts the passengers into the planes
 */
public class CheckInSystem {
    ArrayList<Order> PassengerstoBeMovedList = new ArrayList<Order>();
    Order testOrder;
    treeMapNodeOrderHolder storageNode = null;
    sortedComparator sortComp = new sortedComparator();
    ArrayList<treeMapNodeOrderHolder> sortedOrders = new ArrayList<treeMapNodeOrderHolder>();
    ConcurrentSkipListSet<treeMapNodeOrderHolder> myMap = new ConcurrentSkipListSet<treeMapNodeOrderHolder>(new sortedComparator());


    public CheckInSystem(){
        List<String> cities1 = new ArrayList<String>(Arrays.asList("Stockholm","London","Rome","Paris","Berlin"));
        List<String> cities2 = new ArrayList<String>(Arrays.asList("Stockholm","London","Rome","Paris","Berlin"));

        for(int j=0; j<5; j++){
            for(int i=0; i<5; i++ ){
                if(i!=j){
                    treeMapNodeOrderHolder nodeAdd = new treeMapNodeOrderHolder(cities1.get(i), cities2.get(j) );
                    myMap.add(nodeAdd);
                }
            }
        }

    }

    public ArrayList<treeMapNodeOrderHolder> sortByTheMostPrioritizedRoute(){
        ArrayList<treeMapNodeOrderHolder> sortedOrders;
        sortedOrders = myMap.stream().sorted(new sortedComparator()).collect(Collectors.toCollection(ArrayList::new));
       //System.out.println("First sorted element : " +sortedOrders.get(0).getKeyString() +" "  +sortedOrders.get(0).getNumberOfPassengers());
        return sortedOrders;
    }

    public void printSortedOrders(){
       this.sortedOrders.stream().forEach(e -> System.out.println("SortedNode : " +e.getKeyString() +" " +e.getNumberOfPassengers()));
    }

    public void addOrderToTheCheckInSystem(Order testOrder) {
        Iterator<treeMapNodeOrderHolder> testIterator = myMap.iterator();
        //System.out.println("Attempting to add " + testOrder.getDestination() + testOrder.getDeparture());
        while (testIterator.hasNext()) {
            treeMapNodeOrderHolder currentNode = testIterator.next();
            if (currentNode.getKeyString().equalsIgnoreCase(testOrder.getDeparture()+testOrder.getDestination())) {
                //System.out.println("Found! :" + testOrder.getDestination() + testOrder.getDeparture());
                currentNode.addOrderToOrderArrayList(testOrder);
                break;
            }

        }
    }

    synchronized public void removeOrderFromTheCheckInSystem(Order testOrder) {
        Iterator<treeMapNodeOrderHolder> testIterator = myMap.iterator();
        System.out.println("Attempting to remove " + testOrder.getDeparture() + testOrder.getDestination());
        while (testIterator.hasNext()) {
            treeMapNodeOrderHolder currentNode = testIterator.next();
            if (currentNode.getKeyString().equalsIgnoreCase(testOrder.getDeparture() + testOrder.getDestination())) {
                System.out.println("Found! :" + testOrder.getDestination() + testOrder.getDeparture());
                currentNode.removeNumberOfPassengers(testOrder.getPassengers());
                currentNode.removeOrderFromOrderArrayList(testOrder);
                break;
            }

        }
    }
    }


