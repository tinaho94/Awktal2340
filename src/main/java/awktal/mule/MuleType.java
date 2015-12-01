package awktal.mule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MuleType {
    NONE ("yada yada place holder yada", null),
    ENERGY ("store_pictures/mule_types/energy.png", Resource.ENERGY),
    FOOD ("store_pictures/mule_types/food.png", Resource.FOOD),
    ORE ("store_pictures/mule_types/ore.png", Resource.ORE),
    STINKY ("store_pictures/mule_types/stinky.jpg", Resource.ORE),
    PATRIOTIC ("store_pictures/mule_types/patriotic.jpg", Resource.ENERGY),
    GIRL ("store_pictures/mule_types/girl.jpg", Resource.ENERGY),
    PERSONALITY ("store_pictures/mule_types/personality.jpg", Resource.ORE),
    CHILLER ("store_pictures/mule_types/chiller.jpg", Resource.FOOD);

    private String path;
    private Resource productionType;

    private MuleType(String path, Resource productionType) {
        this.path = path;
        this.productionType = productionType;
    }

    public String getPath() {
        return path;
    }

    public Resource getProductionType() {
        return productionType;
    }

    private static final List<MuleType> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MuleType randomMule()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}