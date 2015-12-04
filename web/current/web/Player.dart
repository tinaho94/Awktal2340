import 'Inventory.dart';
import 'Mule.dart';
import 'Tile.dart';
import 'ResourceType.dart';

class Player {
    final String name;
    final String color;
    Mule mule;
    Inventory inventory;
    List<Tile> tiles = [];

    Player(this.name, this.color) {
        inventory = new Inventory(10, 10, 10, 1000);
    }

    String toString() {
        return "[Name: $name Color: $color]";
    }

    int score() {
        int score = 0;
        score += tiles.length * 500;
        score += inventory.resources[ResourceType.MONEY];
        score += inventory.resources[ResourceType.FOOD] * 30;
        score += inventory.resources[ResourceType.ENERGY] * 25;
        score += inventory.resources[ResourceType.ORE] * 50;
        return score;
    }
}