import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


/**
 * The FlightSimulator creates and runs
 * the Order Generators and the Airplanes.
 * It creates random starting locations for the Airplanes.
 *
 * @author Felix Richnau
 * @version 1.0
 * @since 2017-04-27
 */
public class FlightSimulator {

    /**
     * This method is used to simulate Flight happening.
     * It creates 3 Ordergenerators and starts them.
     * Then it creates 5 Airplanes and gives them random starting locations.
     * Then it starts them too.
     */
    void SimulateFlight(){
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
    }
}
