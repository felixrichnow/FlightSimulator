import java.util.ArrayList;


/**
 * Created by user on 2017-03-27.
 */
public class Airplane extends Thread {
    static int planeCounterStat;
    private final Object lock;
    private final double speedOfthePlanePerMinute = 150;
    public int planeCounter;
    ArrayList<Order> Passengers = new ArrayList<Order>();
    boolean keepRunning = true;
    treeMapNodeOrderHolder firstNode = null;
    private String currentLocation = null, currentDestination = null, currentDep = null;
    private CheckInSystem currentSystem;
    private GoogleObject googleObject;
    private treeMapNodeOrderHolder currentNode;
    private
    ArrayList<treeMapNodeOrderHolder> possibleTreeNodes;
    String color;
    private double currentDistanceToDestination = 0; // Starts at a destination
    private int actualAmountOfPassengers=0;
    private int amountOfPossiblePassengers = 10;
    private boolean airPlaneIsMoving = false;
    private boolean notMoving = true;

    public Airplane(CheckInSystem currentSystem, Object lock, String location) {
        //Passed along arguments
        this.lock = lock;
        this.currentSystem = currentSystem;
        this.currentLocation = location;
        possibleTreeNodes = new ArrayList<treeMapNodeOrderHolder>();
        this.googleObject = new GoogleObject();
        planeCounterStat++;
        this.planeCounter = planeCounterStat;
    }

    //Help function to test
    public void setCurrentDistanceToDestination(double distanceToBeChanged) {
        currentDistanceToDestination = distanceToBeChanged;
    }


    /*
    /* This method will be used to add passengers into the airplane
     */

    public void addPassengers(Order newPassengers) {
        actualAmountOfPassengers += newPassengers.getPassengers();
        Passengers.add(newPassengers);
    }

    public synchronized void getFirstNodeWithLocAndRemove() {
        possibleTreeNodes = currentSystem.sortByTheMostPrioritizedRoute();
        currentNode = possibleTreeNodes.stream().filter(t -> t.getKeyString()
                .startsWith(currentLocation)).findFirst().get();

        if (this.currentNode.getNumberOfPassengers() != 0) {
            notMoving = false;
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
                    //This printout shows you if you tried to add too many passengers
//                    System.out.println("NO SPACE IN PLANE" +planeCounter +"  currentAmountPas: "
//                            +currentAmountOfPossiblePassengers +"actualAmounPas :" +actualAmountOfPassengers
//                            +" PasToBeAdded; " +currentNode.getOrderArrayList().get(j).getPassengers());

                    break;
                }
            }
        }
    }

    @Override
    public void run() {

        notMoving = true;
        keepRunning = true;

        //This should only be called when it needs a new destination
        while (keepRunning == true) {
            if (currentDistanceToDestination <= 0) {
                notMoving = true;
                this.actualAmountOfPassengers = 0;
            }
            while (notMoving == true) {
                // fix this synchronized (lock)
                synchronized (lock) {
                    getFirstNodeWithLocAndRemove();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
            System.out.println("PLANE " + planeCounter + " MOVING 150 KM");
            System.out.println("CurrentDist PLANE" + planeCounter + " " + this.currentDistanceToDestination
                    + " PlaneDepLoc" + planeCounter + " " + this.currentLocation + " PlaneDepDes" + planeCounter + " " + currentDep);
            this.currentDistanceToDestination = this.currentDistanceToDestination - speedOfthePlanePerMinute;
            try {
                Thread.sleep(6000);
                //Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

