
public class GoogleObject {

    /**
     * GoogleObject only exists as a help object
     * to calculate distance between cities.
     * Could be replaced with a Lambda expression.
     *
     * @author Felix Richnau
     * @version 1.0
     * @since 2017-04-27
     */
    private String keyString;

    /**
     * This method is used to calculate the distance between
     * two cities. All it does is convert two Strings into a value
     * for an AirPlane to be able to know how long it supposed to be
     * in the Air (moving).
     *
     * @param currentDep current Departure city to be measured from
     * @param currentDes current Destination city to be measured to
     */
    int gDistance(String currentDep, String currentDes) {
        keyString = currentDep+currentDes;
        int distance = 0;
        //Later if I can implement this so it googles the distance, that would be great
        // Might change this into a lambda expression too
        switch (keyString) {

            case "StockholmLondon":
            case "LondonStockholm":
                distance = 1433;
                break;
            case "StockholmRome":
            case "RomeStockholm":
                distance = 2547;
                break;
            case "StockholmParis":
            case "ParisStockholm":
                distance = 1540;
                break;
            case "BerlinStockholm":
            case "StockholmBerlin":
                distance = 810;
                break;
            case "BerlinRome":
            case "RomeBerlin":
                distance = 1184;
                break;
            case "BerlinParis":
            case "ParisBerlin":
                distance = 878;
                break;
            case "BerlinLondon":
            case "LondonBerlin":
                distance = 932;
                break;
            case "RomeLondon":
            case "LondonRome":
                distance = 1440;
                break;
            case "RomeParis":
            case "ParisRome":
                distance = 1107;
                break;
            case "LondonParis":
            case "ParisLondon":
                distance = 344;
                break;
        }
        return distance;
    }
}



