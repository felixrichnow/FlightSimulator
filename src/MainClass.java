import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class MainClass {
    public static void main(String[] args) {
        FlightSimulator flightSim = new FlightSimulator();
        flightSim.SimulateFlight();
    }
}