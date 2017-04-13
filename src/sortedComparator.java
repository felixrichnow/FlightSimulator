import java.util.Comparator;

public class sortedComparator implements Comparator<treeMapNodeOrderHolder> {


    @Override
    public int compare(treeMapNodeOrderHolder o2, treeMapNodeOrderHolder o1 ) {
        Integer x1 = o1.getNumberOfPassengers();
        Integer x2 = o2.getNumberOfPassengers();
                     return (100* x1.compareTo(x2))+ o2.getKeyString().compareTo(o1.getKeyString());
    }

}




