import java.util.Comparator;

/**
 * A Custom Comparator that checks
 * two listNodeOrderHolders according to their
 * amount of passengers and their keyStrings.
 * Amount of passengers matters more in the sorting
 * than keyString.
 *
 * @author Felix Richnau
 * @version 1.0
 * @since 2017-04-27
 */

public class sortedComparator implements Comparator<listNodeOrderHolder> {

    @Override
    public int compare(listNodeOrderHolder o2, listNodeOrderHolder o1 ) {
        Integer x1 = o1.getNumberOfPassengers();
        Integer x2 = o2.getNumberOfPassengers();
                     return (100* x1.compareTo(x2))+ o2.getKeyString().compareTo(o1.getKeyString());
    }

}




