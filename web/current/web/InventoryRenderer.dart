import 'dart:html';
import 'Inventory.dart';

class InventoryRenderer {
    static DivElement render(Inventory i) {
        DivElement view = new DivElement();
        i.resources.forEach((k,v) {
            SpanElement resource = new SpanElement();
            resource.text = "$k: $v";
            view.children.add(resource);
        });
        return view;
    }
}