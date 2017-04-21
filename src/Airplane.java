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
public class Airplane implements Runnable {
    double currentDistanceToDestination =0; // Starts at a destination
    double speedOfthePlanePerMinute;
    int actualAmountOfPassengers;
    int amountOfPossiblePassengers=150;
    boolean airPlaneIsMoving = false;
    String Departure; // Currentlocation
    String Destination;
    boolean timerCorrect;
    ArrayList<Order> Passengers = new ArrayList<Order>();
    ArrayList<treeMapNodeOrderHolder> possibletreeNodes = new ArrayList<treeMapNodeOrderHolder>();
    boolean running = true;
    treeMapNodeOrderHolder firstNode = null;
    String currentLocation;
    CheckInSystem currentSystem;
    GoogleObject googleObj;
    boolean existsLocation = false;
    treeMapNodeOrderHolder currentNode = null;

    public Airplane(CheckInSystem currentSystem) {
        this.currentDistanceToDestination =0;
        //I think this is in kilo meters
        this.speedOfthePlanePerMinute=14.75;
        timerCorrect = true;
        airPlaneIsMoving = false;
        this.currentSystem=currentSystem;
        GoogleObject googleObj = new GoogleObject();
    }

    public void setCurrentDistanceToDestination(double distanceToBeChanged ){
        currentDistanceToDestination = distanceToBeChanged;
    }

    public double getCurrentDistanceToDestination(){
        return currentDistanceToDestination;
    }

    public boolean isAirPlaneIsMovingTrue (){
        return airPlaneIsMoving;
     }

    public void reachedDestionNation(){
        Passengers.clear();
    }

    public void figureOutWhereToGo(){

    }
    /*
    /* This method will be used to add passengers into the airplane
     */

    public void addPassengers(Order newPassengers){
        if(Destination.equalsIgnoreCase(newPassengers.getDestination())){
            Passengers.add(newPassengers);
        }
        else{
            System.out.println("Tried adding passengers with wrong destination " +
                    "to a plane with different destination");

        }
    }


    @Override
    public void run() {
        int distanceToDestination=0;
        possibletreeNodes = currentSystem.sortByTheMostPrioritizedRoute();
        //users.stream().filter(u -> u.age > 30).collect(Collectors.toList());
        if(possibletreeNodes!= null);
        if(possibletreeNodes.stream().filter(t -> t.getKeyString()
                .startsWith(currentLocation)).findFirst().get() != null ){
            treeMapNodeOrderHolder currentNode = possibletreeNodes.stream().filter(t -> t.getKeyString()
                    .startsWith(currentLocation)).findFirst().get();
            System.out.println("FOUND A NODE TO START : " +currentNode.getKeyString());

            for(int j=0; j<currentNode.getOrderArrayList().size();j++){
                System.out.println( j +" element in OrderList : " +currentNode.getOrderArrayList().get(j).getPassengers());
            }

        }
        if(currentNode!= null)
        //Thread.sleep()

        System.out.println(currentNode.getKeyString());


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


    }




}
