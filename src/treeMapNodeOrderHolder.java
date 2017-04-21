import java.util.ArrayList;


/**
 * Created by user on 2017-04-07.
 */

public class treeMapNodeOrderHolder implements Comparable<treeMapNodeOrderHolder> {

    String keyString;
    String currentDepature;
    String currentDestination;
    int numberOfPassengers;
   ArrayList<Order> orderHolderArrayList;

    public treeMapNodeOrderHolder(Order newOrder) {
        this.keyString = newOrder.getDeparture()+newOrder.getDestination();
        this.currentDepature = newOrder.getDeparture();
        this.currentDestination = newOrder.getDestination();
        this.numberOfPassengers = newOrder.getPassengers();
      orderHolderArrayList = new ArrayList<Order>();
      orderHolderArrayList.add(newOrder);
        currentDepature = null;
        currentDestination = null;
    }


    @Override
    public int compareTo(treeMapNodeOrderHolder that) {
        //return this.id - that.id;
        //return this.familyName.compareTo(that.familyName);
        //return this.name.compareTo(that.name);
        //return this.name.compareTo(that.name)+this.familyName.compareTo(that.familyName);
          // return (1000* (that.numberOfPassengers-this.numberOfPassengers))+this.keyString.compareTo(that.keyString);
            return this.keyString.compareTo(that.keyString);

    }

  public ArrayList<Order> getOrderArrayList(){
      return orderHolderArrayList;
  }

    public void addOrderToOrderArrayList(Order ordertoAdd){
        this.keyString = ordertoAdd.getDeparture()+ordertoAdd.getDestination();
        numberOfPassengers+= ordertoAdd.getPassengers();
        this.currentDepature = ordertoAdd.getDeparture();
        this.currentDestination = ordertoAdd.getDestination();
        orderHolderArrayList.add(ordertoAdd);
    }


    public String getKeyString(){
        return keyString;
    }

    public int getNumberOfPassengers(){
        return numberOfPassengers;
    }

    public void addNumberOfPassengers(int number){
        numberOfPassengers+=number;
    }

    @Override
    public String toString() {
        return keyString;
    }

    public String getDeparture(){
        return currentDepature;
    }

    public String getCurrentDestination(){
        return currentDestination;
    }

}
