import 'Inventory.dart';
import 'Mule.dart';
import 'Tile.dart';
import 'ResourceType.dart';
import 'Race.dart';

class Player {
    final String name;
    final String color;
    Mule mule;
    Inventory inventory;
    List<Tile> tiles = [];
    Race race;

    static Map<Race, int> startingMoney = {
        Race.HUMAN: 600,
        Race.FLAPPER: 1600,
        Race.BONZOID: 1000,
        Race.UGAITE: 1000,
        Race.BUZZITE: 1000,
    };

    Player(this.name, this.color, this.race) {
        inventory = new Inventory(8, 4, 0, startingMoney[race]);
    }

    String toString() {
        return "[Name: $name Color: $color, Race: $race]";
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