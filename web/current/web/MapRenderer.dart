import 'dart:html';

import 'GameMap.dart';
import 'Tile.dart';
import 'TileRenderer.dart';

class MapRenderer {

    static DivElement render(GameMap m) {
        DivElement mapContainer = new DivElement();
        mapContainer.style.width = "800px";
        mapContainer.style.height = "500px";
        mapContainer.style.overflow = "hidden";
        for (List<Tile> r in m.tiles) {
            DivElement row = new DivElement();
            row.style.overflow = "hidden";
            row.style.width = "100%";
            row.style.height = "20%";
            for (Tile t in r) {
                DivElement t_view = TileRenderer.render(t);
                t_view.classes.add("${t.type}");
                t_view.style.display = "inline-block";
                t_view.style.border = "1px solid black";
                t_view.style.margin = "0px";
                t_view.style.width = "10.5%";
                t_view.style.height = "98%";
                row.children.add(t_view);
            }
            mapContainer.children.add(row);
        }
        return mapContainer;
    }
}