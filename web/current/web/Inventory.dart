import 'ResourceType.dart';

class Inventory {
    Map<ResourceType, int> resources = {};

    Inventory(int food, int energy, int ore, int money) {
        resources[ResourceType.FOOD] = food;
        resources[ResourceType.ENERGY] = energy;
        resources[ResourceType.ORE] = ore;
        resources[ResourceType.MONEY] = money;
    }
}