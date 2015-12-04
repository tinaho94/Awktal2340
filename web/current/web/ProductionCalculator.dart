import 'dart:html';

import 'Inventory.dart';
import 'MuleType.dart';
import 'ResourceType.dart';
import 'TileType.dart';
import 'MapRenderer.dart';
import 'GameState.dart';
import 'Tile.dart';
import 'GameState.dart';

class ProductionCalculator {

    static Map<TileType, Map<MuleType, Inventory>> productionValues = {
        TileType.PLAIN: {
            MuleType.FOOD: new Inventory(2, 0, 0, 0),
            MuleType.ENERGY: new Inventory(0, 3, 0, 0),
            MuleType.ORE: new Inventory(0, 0, 1, 0),
        },
        TileType.RIVER: {
            MuleType.FOOD: new Inventory(4, 0, 0, 0),
            MuleType.ENERGY: new Inventory(0, 2, 0, 0),
            MuleType.ORE: new Inventory(0, 0, 0, 0),
        },
        TileType.MOUNTAIN1: {
            MuleType.FOOD: new Inventory(1, 0, 0, 0),
            MuleType.ENERGY: new Inventory(0, 1, 0, 0),
            MuleType.ORE: new Inventory(0, 0, 2, 0),
        },
        TileType.MOUNTAIN2: {
            MuleType.FOOD: new Inventory(1, 0, 0, 0),
            MuleType.ENERGY: new Inventory(0, 1, 0, 0),
            MuleType.ORE: new Inventory(0, 0, 3, 0),
        },
        TileType.MOUNTAIN3: {
            MuleType.FOOD: new Inventory(1, 0, 0, 0),
            MuleType.ENERGY: new Inventory(0, 1, 0, 0),
            MuleType.ORE: new Inventory(0, 0, 4, 0),
        },
    };

    static Inventory calculateProduction(TileType tile, MuleType mule) {
        return productionValues[tile][mule];
    }

    static DivElement calculateAllProduction(GameState gameState) {
        DivElement display = MapRenderer.render(gameState.map);
        for (DivElement row in display.children) {
            for (DivElement tile_view in row.children) {
                Tile t = getMatchingTile(tile_view, gameState);
                if (t.mule != null) {
                    Inventory results = calculateProduction(t.type, t.mule.type);
                    print("results ${results.toString()}");
                    DivElement summary = new DivElement();
                    summary.style.top = "20px";
                    summary.style.position = "absolute";
                    summary.text = "";
                    if (t.owner.inventory.resources[ResourceType.ENERGY] > 0) {
                        summary.text += "-1 energy\n";
                        t.owner.inventory.resources[ResourceType.ENERGY]--;
                        t.owner.inventory.add(results);
                        summary.text += results.toString();
                    }
                    print("adding production note");

                    summary.style.zIndex = "300";
                    summary.style.fontSize = "10px";
                    tile_view.children.add(summary);
                }
            }
        }
        return display;
    }

    static Tile getMatchingTile(DivElement view, GameState gameState) {
        int x = int.parse(view.id.split("-")[0]);
        int y = int.parse(view.id.split("-")[1]);
        return gameState.map.tiles[y][x];
    }
}