package awktal.mule;

import java.util.Iterator;

public class MapIterator implements Iterator<Tile> {
    int currX;
    int currY;
    Map map;

    /**
     * Creates a map iterator that iterates over all the tiles in a map.
     * @param map the map to iterate over.
    */
    public MapIterator(Map map) {
        this.map = map;
        currX = 0;
        currY = 0;
    }

    public boolean hasNext() {
        return currX < 9 && currY < 5;
    }

    /**
     * Returns the next tile.
     * @return the next tile.
    */
    public Tile next() {
        Tile next = map.getTile(currX, currY);
        if (currY == 4) {
            currY = 0;
            currX++;
        } else {
            currY++;
        }
        return next;
    }
}