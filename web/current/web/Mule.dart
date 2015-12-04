import 'MuleType.dart';

class Mule {

    static Map<MuleType, String> imagePaths = {
        MuleType.FOOD: "mule_images/food.png",
        MuleType.ENERGY: "mule_images/energy.png",
        MuleType.ORE: "mule_images/ore.png",
    };

    MuleType type = MuleType.NONE;

    Mule();

    void outfit(t) {
        type = t;
    }

    static String getImagePath(MuleType type) {
        return imagePaths[type];
    }
}