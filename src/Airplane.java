import java.util.ArrayList;

/**
 * The object Airplane is also a thread and
 * will simulate an Airplane flying from
 * one city to another. It will keep track
 * of its departure city and its detination city.
 * It will also take passengers from a checkInSystem
 * whenever it's standing still. It updates itself
 * when it is moving/flying to simulate distance
 * being travelled.
 *
 * @author Felix Richnau
 * @version 1.0
 * @since 2017-04-27
 */
public class Airplane extends Thread {
    private static int planeCounterStat;
    private final Object lock;
    //Actual speed per minute is 15km but since it's a simulator we make it 150, to waste less time
    private final int speedOfthePlanePerMinute = 150;
    //Will make planeCounter public so if necessary easily accessible, only keep track of planes "id"
    private int planeCounter;
    private ArrayList<Order> Passengers = new ArrayList<>();
    private String currentLocation = null, currentDestination = null, currentDep = null, color=null;
    private CheckInSystem currentSystem;
    private GoogleObject googleObject = null;
    private listNodeOrderHolder currentNode = null;
    private ArrayList<listNodeOrderHolder> possibleTreeNodes;
    private int currentDistanceToDestination = 0; // Starts at a destination
    private int actualAmountOfPassengers=0, getFirstCheck=0, amountOfPossiblePassengers = 10;
    private boolean airPlaneIsMoving = false, planeIsFull = false, notMoving = true, keepRunning = true;

    public Airplane(CheckInSystem currentSystem, Object lock, String location) {
        //Passed along arguments
        this.lock = lock;
        this.currentSystem = currentSystem;
        this.currentLocation = location;
        possibleTreeNodes = new ArrayList<>();
        this.googleObject = new GoogleObject();
        planeCounterStat++;
        this.planeCounter = planeCounterStat;
    }

    //Help function to test
    public void setCurrentDistanceToDestination(int distanceToBeChanged) {
        currentDistanceToDestination = distanceToBeChanged;
    }

    /**
     * Method removeOrderToOrderArrayList
     * This method is used to add passengers to an AirPlane.
     * It also keeps track of how many passengers has been added
     * in the variable actualAmountOfPassengers.
     * @param newPassengers passengers to be added to the AirPlane
     */
    private void addPassengers(Order newPassengers) {
        actualAmountOfPassengers += newPassengers.getPassengers();
        Passengers.add(newPassengers);
    }

    /**
     * Method getFirstNodeWithLockAndRemove
     * This method is locked down when used. It's incredibly
     * important for the Flight Simulation.
     * It first gets a sort ArrayList of all Nodes in the CheckInSystem.
     * Then it find the first Node in that list which matches with
     * the AirPlanes current location. If the Node has passengers in
     * it, they will be added to the AirPlane and removed from the
     * CheckInSystem. When adding passengers, the AirPlane does this
     * by checking that there's enough space and add new passengers
     * til there's no more space. Or til it at 10 times added passenger orders.
     */
    private synchronized void getFirstNodeWithLocAndRemove() {
        possibleTreeNodes = currentSystem.sortByTheMostPrioritizedRoute();
        currentNode = possibleTreeNodes.stream().filter(t -> t.getKeyString()
                .startsWith(currentLocation)).findFirst().get();

        if (this.currentNode.getNumberOfPassengers() != 0) {
            getFirstCheck++;
            System.out.println();
            System.out.println("DISTANCE P" + planeCounter + " " + googleObject.gDistance(currentNode.getCurrentDeparture(),
                    currentNode.getCurrentDestination()) + " StartDep : "
                    + currentNode.getCurrentDeparture() + " StartDES : "
                    + currentNode.getCurrentDestination());
            currentDistanceToDestination = googleObject.gDistance(currentNode.getCurrentDeparture(),
                    currentNode.getCurrentDestination());
            currentLocation = currentNode.getCurrentDestination();
            currentDep = currentNode.getCurrentDeparture();
            int currentAmountOfPossiblePassengers = amountOfPossiblePassengers;

            for (int j = 0; j < currentNode.getOrderArrayList().size(); j++) {
                //System.out.println(+j + " element in OrderList : " + currentNode.getOrderArrayList().get(j).getPassengers());
                if (actualAmountOfPassengers + currentNode.getOrderArrayList().get(j).getPassengers()
                        <= currentAmountOfPossiblePassengers) {
                    //Adding passengers to the plane but removing them from the System
                    currentAmountOfPossiblePassengers -= currentNode.getOrderArrayList().get(j).getPassengers();
                    this.addPassengers(currentNode.getOrderArrayList().get(j));
                    currentSystem.removeOrderFromTheCheckInSystem(currentNode.getOrderArrayList().get(j));
                }
                else{
                    planeIsFull = true;
                    //This printout shows you if you tried to add too many passengers
//                    System.out.println("NO SPACE IN PLANE" +planeCounter +"  currentAmountPas: "
//                            +currentAmountOfPossiblePassengers +"actualAmounPas :" +actualAmountOfPassengers
//                            +" PasToBeAdded; " +currentNode.getOrderArrayList().get(j).getPassengers());
                    break;
                }
            }
        }
    }

    /**
     * Method run
     * This method is the AirPlane run method, it will always keep running.
     * If the plane is standing still, it will check if there's any Order
     * that matches with it's current location in the CheckInSystem.
     * If there's an Order in that Node with passengers it will add Orders
     * from that Node(in the CheckInSystem) to the AirPlane as long as there's
     * space in the AirPlane during 10 periods of time. When the time is out
     * or if the plane is full it will start moving
     * @Override the run() function. As an AirPlane is a runnable object.
     */

    @Override
    public void run() {

        notMoving = true;
        keepRunning = true;
        planeIsFull = false;
        getFirstCheck = 0;
        //Arbitrary condition to keep whileloop going
        while (keepRunning) {
            if (currentDistanceToDestination <= 0) {
                notMoving = true;
                planeIsFull = false;
                getFirstCheck = 0;
                this.actualAmountOfPassengers = 0;
            }
            //If the airplane stops moving it should check if it's ready to leave
            while (notMoving) {
                if(getFirstCheck == 9 || planeIsFull){
                    notMoving=false;
                }
                //This should only be called when it needs a new destination + new passengers
                //But because the threadsafety of ConcurrentSkipListSet has been
                //Questionable this method is locked down and does a lot in one go
                synchronized (lock) {
                    getFirstNodeWithLocAndRemove();
                    //Print to check it waits 10 rounds of adding Orders before flying
                    //System.out.println("Check(ORDERSADDED)" +getFirstCheck +" Plane" +planeCounter);
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //Help switcch case, just to print out different colors
            switch (planeCounter) {
                case 1:
                    color = "\u001B[31m";
                    break;
                case 2:
                    color = "\u001B[32m";
                    break;
                case 3:
                    color = "\u001B[33m";
                    break;
                case 4:
                    color = "\u001B[34m";
                    break;
                case 5:
                    color = "\u001B[35m";
                    break;
            }
            currentSystem.printSortedOrders(color);
            System.out.println("CurrentDist PLANE" + planeCounter + " " + this.currentDistanceToDestination
                    + " PlaneDepLoc" + planeCounter + " " + this.currentLocation + " PlaneDepDes" + planeCounter + " " + currentDep
            + " MOVING 150 KM");
            this.currentDistanceToDestination = this.currentDistanceToDestination - speedOfthePlanePerMinute;
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

