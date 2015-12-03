import 'dart:html';

import 'TileType.dart';
import 'Tile.dart';

class TileRenderer {
    static Map<TileType, String> tile_images = {
        TileType.RIVER: "map_pictures/water.png",
        TileType.PLAIN: "map_pictures/grass.png",
        TileType.MOUNTAIN1: "map_pictures/mount1.png",
        TileType.MOUNTAIN2: "map_pictures/mount2.png",
        TileType.MOUNTAIN3: "map_pictures/mount3.png",
        TileType.BUILDING: "map_pictures/building.png"
    };

    static DivElement render(Tile t) {
        DivElement view = new DivElement();
        ImageElement tile_image = new ImageElement();
        tile_image.src = tile_images[t.type];
        tile_image.style.height = "100%";
        tile_image.style.width = "100%";
        view.children.add(tile_image);
        return view;
    }
}