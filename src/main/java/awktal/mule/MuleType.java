package awktal.mule;

public enum MuleType {
    NONE ("yada yada place holder yada", null),
    ENERGY ("store_pictures/mule_types/energy.png", Resource.ENERGY),
    FOOD ("store_pictures/mule_types/food.png", Resource.FOOD),
    ORE ("store_pictures/mule_types/ore.png", Resource.ORE);

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
}