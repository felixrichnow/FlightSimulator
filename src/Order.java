
/**
 * An Order contains all information necessary
 * information for an AirPlane to be able to
 * get and transport passengers.
 * It has their Departure,Destination
 * ticketClass and how many passengers they
 * are stored in the Order.
 *
 * @author Felix Richnau
 * @version 1.0
 * @since 2017-04-27
 */
public class Order {
    private int passengers; // 1-5 passengers per order
    private String departure; // where the plane leaves from
    private String destination; // where the plane will go
    private int ticketClass; // 1-3 where 1 is first-class, 2 is business and 3 economy

    public Order (int passengers, String departure, String destination, int ticketClass){
        this.passengers=passengers;
        this.departure=departure;
        this.destination=destination;
        this.ticketClass=ticketClass;
    }

    /**
     * Method getPassengers
     * This method is used to get access to the Orders passengers.
     * @return passengers the Orders passengers(int)
     */
    public int getPassengers() {
        return passengers;
    }

    /**
     * Method getTicketClass
     * This method is used to get access to the Orders ticket class.
     * @return ticketClass the Orders ticket class(int)
     */
    int getTicketClass() {
        return ticketClass;
    }

    /**
     * Method getDeparture
     * This method is used to get access to the Orders departure.
     * @return departure the Orders departure city (String)
     */
    String getDeparture() {
        return departure;
    }

    /**
     * Method getDestination
     * This method is used to get access to the Orders destination.
     * @return destination the Orders destination city (String)
     */
    String getDestination() {
        return destination;
    }

}
