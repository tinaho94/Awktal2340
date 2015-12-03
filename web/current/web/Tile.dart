import 'Player.dart';
// import 'Mule.dart';
import 'TileType.dart';
// import 'Inventory.dart';
// import 'ProductionCalculator.dart';

class Tile {
    int x;
    int y;
    Player owner;
    TileType type;
    // Mule mule;

    Tile(int this.x, int this.y, TileType this.type);

    // Inventory calculateProduction() {
    //     if (this.mule == null) {
    //         return new Inventory();
    //     } else {
    //         ProductionCalculator.calc(this.type, mule.type);
    //     }
    // }

    String toString() {
        return "($x, $y)";
    }

    String classString() {
        return "${type.toString().substring(type.toString().indexOf('.')+1)}";
    }
}