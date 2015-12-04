import 'dart:html';
import 'Inventory.dart';

class InventoryRenderer {
    static DivElement render(Inventory i) {
        DivElement view = new DivElement();
        i.resources.forEach((k,v) {
            DivElement resource = new DivElement();
            String name = "${k.toString().substring(k.toString().indexOf('.')+1).toLowerCase()}";
            resource.text = "$name: $v";
            resource.style.display = "inline-block";
            resource.style.padding = "5px";
            view.children.add(resource);
        });
        return view;
    }
}