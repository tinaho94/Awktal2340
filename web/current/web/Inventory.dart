import 'ResourceType.dart';

class Inventory {
    Map<ResourceType, int> resources = {};

    Inventory(int food, int energy, int ore, int money) {
        resources[ResourceType.FOOD] = food;
        resources[ResourceType.ENERGY] = energy;
        resources[ResourceType.ORE] = ore;
        resources[ResourceType.MONEY] = money;
    }

    void add(Inventory other) {
        resources.forEach((k, v) {
            resources[k] += other.resources[k];
        });
    }

    String toString() {
        return "+${resources[ResourceType.FOOD]} food\n+${resources[ResourceType.ENERGY]} energy\n+${resources[ResourceType.ORE]} ore";
    }
}