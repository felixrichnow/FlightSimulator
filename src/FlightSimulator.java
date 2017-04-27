import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by user on 2017-03-27.
 */


public class FlightSimulator {


    public void SimulateFlight(){
        final Object lock = new Object();
        CheckInSystem testCheckInSystem = new CheckInSystem();
        Random randomGenerator = new Random();
        OrderGenerator mathGen = new OrderGenerator();
        //OrderGenerators
        for(int i=0; i<3; i++){
            Thread currentThread = new Thread(new OrderGenerator(testCheckInSystem));
            currentThread.start();
        }
        //Airplanes
        for(int i=0; i<5; i++){
            String startLoc = mathGen.figureOutDepartureAndDestination(randomGenerator.nextInt(5) + 1);
            Thread currentAirPlane = new Thread(new Airplane(testCheckInSystem,lock,startLoc));
            currentAirPlane.start();
        }
//        Airplane testAirPlane = new Airplane(testCheckInSystem,lock,"London");
//        Thread thread3 = new Thread(testAirPlane);
//        thread3.start();
//
//        Airplane testAirPlane2 = new Airplane(testCheckInSystem,lock,"Stockholm");
//        Thread thread4 = new Thread(testAirPlane2);
//        thread4.start();
//
//        Airplane testAirPlane3 = new Airplane(testCheckInSystem,lock,"Berlin");
//        Thread thread5 = new Thread(testAirPlane3);
//        thread5.start();
//
//        Airplane testAirPlane4 = new Airplane(testCheckInSystem,lock,"Paris");
//        Thread thread6 = new Thread(testAirPlane4);
//        thread6.start();
//
//        Airplane testAirPlane5 = new Airplane(testCheckInSystem,lock,"Rome");
//        Thread thread7 = new Thread(testAirPlane5);
//        thread7.start();
    }
}
