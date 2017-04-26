import com.sun.org.apache.xml.internal.utils.CharKey;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by user on 2017-03-27.
 */
public class Airplane extends Thread {
    double currentDistanceToDestination = 0; // Starts at a destination
    double speedOfthePlanePerMinute;
    int actualAmountOfPassengers;
    int amountOfPossiblePassengers = 150;
    boolean airPlaneIsMoving = false;
    ArrayList<Order> Passengers = new ArrayList<Order>();
    boolean running = true;
    treeMapNodeOrderHolder firstNode = null;
    String currentLocation;
    String currentDestination;
    CheckInSystem currentSystem;
    GoogleObject googleObject;
    treeMapNodeOrderHolder currentNode;
    String currentDep;
    public int planeCounter;
    private boolean notMoving;
    ArrayList<treeMapNodeOrderHolder> possibleTreeNodes = new ArrayList<treeMapNodeOrderHolder>();

    public Airplane(CheckInSystem currentSystem) {
        this.currentSystem=currentSystem;
        currentDistanceToDestination = 0;
        //I think this is in kilo meters
        speedOfthePlanePerMinute = 15;
        airPlaneIsMoving = false;
        currentSystem = currentSystem;
        currentLocation = "London";
        currentDestination = null;
        this.notMoving=true;
        ArrayList<treeMapNodeOrderHolder> possibleTreeNodes = new ArrayList<treeMapNodeOrderHolder>();
        this.googleObject = new GoogleObject();
    }

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
        treeMapNodeOrderHolder currentNode = possibleTreeNodes.stream().filter(t -> t.getKeyString()
                .startsWith(currentLocation)).findFirst().get();

        if (currentNode.getNumberOfPassengers() != 0) {
            notMoving = false;
            System.out.println("CURRENTNODE : " + currentNode.getDeparture());
            System.out.println("CURRENTNDES : " + currentNode.getCurrentDestination());
            System.out.println("DISTANCE : " + googleObject.gDistance(currentNode.getDeparture(), currentNode.getCurrentDestination()));
            System.out.println("PLANE" +planeCounter + googleObject.gDistance(currentNode.getDeparture(), currentNode.getCurrentDestination()));
            currentDistanceToDestination = googleObject.gDistance(currentNode.getDeparture(), currentNode.getCurrentDestination());
            currentLocation = currentNode.currentDestination;

            for (int j = 0; j < currentNode.getOrderArrayList().size(); j++) {
                //System.out.println(+j + " element in OrderList : " + currentNode.getOrderArrayList().get(j).getPassengers());
                if (actualAmountOfPassengers + currentNode.getOrderArrayList().get(j).getPassengers() <= amountOfPossiblePassengers) {
                    //Adding passengers to the plane but removing them from the System
                    amountOfPossiblePassengers -= currentNode.getOrderArrayList().get(j).getPassengers();
                    addPassengers(currentNode.getOrderArrayList().get(j));
                    currentSystem.removeOrderFromTheCheckInSystem(currentNode.getOrderArrayList().get(j));
                }
            }

        }
    }

        @Override
        public void run (){
            GoogleObject googleObj = new GoogleObject();
            notMoving = true;
            boolean keepRunning = true;

            //This should only be called when it needs a new destination
            while (keepRunning == true) {
                if (currentDistanceToDestination <= 0) {
                    notMoving = true;
                }
                while (notMoving == true) {
                    // fix this synchronized (lock)
                    this.getFirstNodeWithLocAndRemove();
                    try {
                        this.currentSystem.printSortedOrders();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("PLANE :" + planeCounter + " MOVING 15 KM");
                System.out.println("Current Dist: " + this.currentDistanceToDestination);
                System.out.println("PlaneDepLoc: " + this.currentLocation + " PlaneDepDes" + currentDep);
                this.currentDistanceToDestination = this.currentDistanceToDestination - this.speedOfthePlanePerMinute;
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }








            //{
//            for(int i=0; i<possibletreeNodes.size();i++){
//                    if(possibletreeNodes.get(i) != null){
//                        if(possibletreeNodes.get(i).currentDepature.equalsIgnoreCase(currentLocation)){
//                            Passengers = possibletreeNodes.get(i).getOrderArrayList();
//                            actualAmountOfPassengers = possibletreeNodes.get(i).getNumberOfPassengers();
//                            //distanceToDestination = googleObj.gDistance(firstNode.currentDepature,firstNode.currentDestination);
//                            this.Destination=possibletreeNodes.get(i).currentDestination;
//                            currentSystem.myMap.remove(possibletreeNodes.get(i));
//                            break;
//                        }
//                    }

            //}

        //}



        /*
        System.out.println("land");
        System.out.println("modify the plan");
        */

          /*  System.out.print("PLANE IS ALIVE 10 SECONDS HAVE PASSED \n");
            System.out.print("IsMoving?  " + airPlaneIsMoving + " \n");
            if ((!airPlaneIsMoving) & timerCorrect) {
                System.out.print("Distance to destination: " + currentDistanceToDestination + " \n");
                currentDistanceToDestination = currentDistanceToDestination - speedOfthePlanePerMinute;

                //We make sure to check if it goes negative and sets it to 0 then
                int checkingDistance = (int) currentDistanceToDestination;
                if (checkingDistance < 14) {
                    System.out.print("DESTINATION REACHED \n");
                    airPlaneIsMoving = true;

                }
            }*/



