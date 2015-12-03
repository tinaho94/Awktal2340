import 'Inventory.dart';

class Player {
    final String name;
    final String color;
    Inventory inventory;

    Player(this.name, this.color) {
        inventory = new Inventory(10, 10, 10, 1000);
    }

    String toString() {
        return "[Name: $name Color: $color]";
    }
}