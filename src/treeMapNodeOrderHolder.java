import java.util.ArrayList;


/**
 * Created by user on 2017-04-07.
 */

public class treeMapNodeOrderHolder implements Comparable<treeMapNodeOrderHolder> {

    String keyString;
    int numberOfPassengers;
   ArrayList<Order> orderHolderArrayList;

    public treeMapNodeOrderHolder(Order newOrder) {
        this.keyString = newOrder.getDeparture()+newOrder.getDestination();
        this.numberOfPassengers = newOrder.getPassengers();
      orderHolderArrayList = new ArrayList<Order>();
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
        numberOfPassengers+= ordertoAdd.getPassengers();
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



}
