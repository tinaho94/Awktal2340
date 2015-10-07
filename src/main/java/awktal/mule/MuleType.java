package awktal.mule;

public enum MuleType {
    NONE ("yada yada place holder yada"),
    ENERGY ("store_pictures/mule_types/energy.png"),
    FOOD ("store_pictures/mule_types/food.png"),
    ORE ("store_pictures/mule_types/ore.png");

    private String path;

    private MuleType(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
}