import 'dart:html';

import 'GameMap.dart';
import 'Tile.dart';
import 'TileRenderer.dart';
import 'MuleType.dart';
import 'Mule.dart';

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
            row.style.height = "19%";
            for (Tile t in r) {
                DivElement t_view = TileRenderer.render(t);
                t_view.id = "${t.x}-${t.y}";
                t_view.classes.add("${t.classString()}");
                t_view.classes.add("tile");
                t_view.style.display = "inline-block";
                t_view.style.border = "2px solid #EEE";
                if (t.owner != null) {
                    t_view.style.borderColor = t.owner.color;
                }
                t_view.style.margin = "0px";
                t_view.style.width = "10.5%";
                t_view.style.height = "96%";

                if (t.mule != null) {
                    t_view.children.insert(0, renderMule(t.mule.type));
                }
                t_view.style.overflow = "hidden";
                row.children.add(t_view);
            }
            mapContainer.children.add(row);
        }
        return mapContainer;
    }

    static DivElement renderMule(MuleType type) {
        DivElement mule = new DivElement();
        // mule.style.backgroundColor = "red";
        mule.style.width = "100%";
        mule.style.height = "100%";
        mule.style.position = "relative";
        mule.style.top = "0px";
        mule.style.zIndex = "100";

        ImageElement muleImage = new ImageElement();
        muleImage.src = Mule.getImagePath(type);
        muleImage.style.width = "100%";
        muleImage.style.height = "100%";

        mule.children.add(muleImage);
        return mule;
    }
}