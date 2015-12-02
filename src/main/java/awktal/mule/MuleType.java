package awktal.mule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum MuleType {
    NONE ("yada yada place holder yada", null),
    ENERGY ("store_pictures/mule_types/energy.png", new Resource[] {Resource.ENERGY}),
    FOOD ("store_pictures/mule_types/food.png", new Resource[] {Resource.FOOD}),
    ORE ("store_pictures/mule_types/ore.png", new Resource[] {Resource.ORE}),
    STINKY ("store_pictures/mule_types/stinky.png", new Resource[] {Resource.ORE, Resource.ORE}),
    PATRIOTIC ("store_pictures/mule_types/patriotic.png", new Resource[] {
        Resource.ENERGY, Resource.ORE, Resource. FOOD}),
    GIRL ("store_pictures/mule_types/girl.png", new Resource[] {
        Resource.ENERGY, Resource.ENERGY}),
    PERSONALITY ("store_pictures/mule_types/personality.png", new Resource[] {
        Resource.ENERGY, Resource. ORE}),
    CHILLER ("store_pictures/mule_types/chiller.png", new Resource[] {
        Resource.FOOD, Resource.FOOD});

    private String path;
    private Resource[] productionTypes;

    private MuleType(String path, Resource[] productionTypes) {
        this.path = path;
        this.productionTypes = productionTypes;
    }

    public String getPath() {
        return path;
    }

    public Resource[] getProductionTypes() {
        return productionTypes;
    }

    private static final List<MuleType> VALUES =
        Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static MuleType randomMule()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}