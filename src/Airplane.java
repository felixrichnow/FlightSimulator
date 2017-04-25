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
    double currentDistanceToDestination =0; // Starts at a destination
    double speedOfthePlanePerMinute;
    int actualAmountOfPassengers;
    int amountOfPossiblePassengers=150;
    boolean airPlaneIsMoving = false;
    ArrayList<Order> Passengers = new ArrayList<Order>();
    ArrayList<treeMapNodeOrderHolder> possibletreeNodes = new ArrayList<treeMapNodeOrderHolder>();
    boolean running = true;
    treeMapNodeOrderHolder firstNode = null;
    String currentLocation;
    String currentDestination;
    String currentDestionation;
    CheckInSystem currentSystem;
    GoogleObject googleObj;
    boolean existsLocation = false;
    treeMapNodeOrderHolder currentNode = null;
    String currentDep;

    public Airplane(CheckInSystem currentSystem) {
        this.currentDistanceToDestination =0;
        //I think this is in kilo meters
        this.speedOfthePlanePerMinute=15;
        airPlaneIsMoving = false;
        this.currentSystem=currentSystem;
        currentLocation=null;
        currentDestination=null;
    }

    public void setCurrentDistanceToDestination(double distanceToBeChanged ){
        currentDistanceToDestination = distanceToBeChanged;
    }


    /*
    /* This method will be used to add passengers into the airplane
     */

    public void addPassengers(Order newPassengers){
        actualAmountOfPassengers+=newPassengers.getPassengers();
        Passengers.add(newPassengers);
    }


    @Override
    public void run() {
    GoogleObject googleObj = new GoogleObject();
    boolean notMoving = true;
    boolean keepRunning = true;

        //This should only be called when it needs a new destination
        while( keepRunning== true ){

            if( currentDistanceToDestination <= 0 ){
                notMoving = true;
            }

            while(notMoving == true){
                possibletreeNodes = currentSystem.sortByTheMostPrioritizedRoute();
                /*if(currentDestination != null){

                }*/
                treeMapNodeOrderHolder currentNode = possibletreeNodes.stream().filter(t -> t.getKeyString()
                        .startsWith(currentLocation)).findFirst().get();
                if(currentNode.getNumberOfPassengers() != 0 ) {
                    notMoving = false;
                    System.out.println("CURRENTNODE : " + currentNode.getDeparture());
                    System.out.println("CURRENTNDES : " + currentNode.getCurrentDestination());
                    System.out.println("DISTANCE : " + googleObj.gDistance(currentNode.getDeparture(),currentNode.getCurrentDestination()));
                    currentDistanceToDestination = googleObj.gDistance(currentNode.getDeparture(),currentNode.getCurrentDestination());
                    currentDep = currentNode.getDeparture();
                    currentLocation = currentNode.currentDestination;


                    for (int j = 0; j < currentNode.getOrderArrayList().size(); j++) {
                        //System.out.println(+j + " element in OrderList : " + currentNode.getOrderArrayList().get(j).getPassengers());
                        if (actualAmountOfPassengers + currentNode.getOrderArrayList().get(j).getPassengers() <= amountOfPossiblePassengers)
                            //Adding passengers to the plane but removing them from the System
                            this.amountOfPossiblePassengers -= currentNode.getOrderArrayList().get(j).getPassengers();
                            this.addPassengers(currentNode.getOrderArrayList().get(j));
                            currentSystem.removeOrderFromTheCheckInSystem(currentNode.getOrderArrayList().get(j));
                    }
                }
                else{
                    try {
                        currentSystem.printSortedOrders();
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
                System.out.println("MOVING 70 KM");
            System.out.println("Current Dist: "+currentDistanceToDestination);
            System.out.println("PlaneDepLoc: "+currentLocation +" PlaneDepDes" +currentDep);
            currentDistanceToDestination=currentDistanceToDestination-speedOfthePlanePerMinute;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        //if(currentNode!= null)

        //System.out.println(currentNode.getKeyString());
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



