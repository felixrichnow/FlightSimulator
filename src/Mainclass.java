import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



public class Mainclass {


    public static void main(String[] args) {
    CheckInSystem testCheckInSystem = new CheckInSystem();
    OrderGenerator testGenerator = new OrderGenerator(testCheckInSystem);
    OrderGenerator testGenerator2 = new OrderGenerator(testCheckInSystem);
    OrderGenerator testGenerator3 = new OrderGenerator(testCheckInSystem);
    Thread thread1 = new Thread(testGenerator);
    thread1.start();
        Thread thread2 = new Thread(testGenerator2);
        thread2.start();

        Airplane testAirPlane = new Airplane(testCheckInSystem);
        testAirPlane.currentLocation = "London";
        Thread thread3 = new Thread(testAirPlane);
        thread3.start();
    }
}