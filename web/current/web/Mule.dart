import 'MuleType.dart';

class Mule {
    MuleType type = MuleType.NONE;

    Mule();

    void outfit(t) {
        type = t;
    }
}