package awktal.mule;

import java.util.Random;

/**
 * A round random event generator.
 * This is an example of using a strategy pattern.
 * It allows us to define a strategy at runtime (at the start of each round).
*/
public class RoundRandomEventGenerator {
    static RoundRandomEvent[] events = {
        new EarthquakeEvent(),
        new FloodEvent(),
        new CorrosivePlantsEvent(),
    };

    /**
     * Generates a random round/global event that can be executed.
     * @return A new reference to the random event that can be executed.
    */
    public static RoundRandomEvent getRandomEvent() {
        Random generator = new Random();
        int event = generator.nextInt(events.length);
        return events[event];
    }
}