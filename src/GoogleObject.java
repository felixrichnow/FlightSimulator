
public class GoogleObject {

    public GoogleObject() {
    }
    //Later if I can implement this so it googles the distance, that would be great
    // Might change this into a lambda expression too
    String keyString;
    public double gDistance(String currentDep, String currentDes) {
        keyString = currentDep+currentDes;
        keyString.toLowerCase();
        double distance = 0;
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
            case "BerlinLondon":
            case "LondonBerlin":
                distance = 932;
            case "RomeLondon":
            case "LondonRome":
                distance = 1440;
            case "RomeParis":
            case "ParisRome":
                distance = 1107;
            case "LondonParis":
            case "ParisLondon":
                distance = 344;
        }
        return distance;
    }
}



