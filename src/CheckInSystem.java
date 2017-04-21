import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
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
    TreeMap<treeMapNodeOrderHolder, treeMapNodeOrderHolder> myMap = new TreeMap<>();

    public CheckInSystem(){

    }

    public ArrayList<treeMapNodeOrderHolder> sortByTheMostPrioritizedRoute(){
       sortedOrders = myMap.keySet().stream().sorted(new sortedComparator()).collect(Collectors.toCollection(ArrayList::new));
       sortedOrders.stream().forEach(e -> System.out.println("SortedNode : " +e.getKeyString() +" " +e.getNumberOfPassengers()));
       System.out.println("First sorted element : " +sortedOrders.get(0).getKeyString() +" "  +sortedOrders.get(0).getNumberOfPassengers());
        return sortedOrders;
        //TODO go through the whole HashTable and check what passengers should be moved
        // TODO also maybe prioritize what plane should go first
    }


    public void addOrderToTheCheckInSystem(Order testOrder){
        treeMapNodeOrderHolder testKeyNode = new treeMapNodeOrderHolder(testOrder);
        if (myMap.get(testKeyNode) != null) {
            treeMapNodeOrderHolder currentNode = myMap.get(testKeyNode);
            currentNode.addOrderToOrderArrayList(testOrder);
            myMap.put(currentNode, currentNode);
        } else {
            treeMapNodeOrderHolder currentNode = new treeMapNodeOrderHolder(testOrder);
            myMap.put(currentNode, currentNode);
        }
    }

}
