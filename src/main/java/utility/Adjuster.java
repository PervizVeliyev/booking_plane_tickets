package utility;

import entity.Airport;

import java.time.Duration;

public class Adjuster {
    public static String airportAdjuster(Airport airport){
        String space = " ".repeat(11 - airport.toString().length());
        return airport.toString().concat(space);
    }

    public static String durationAdjuster(Duration duration){
        String space = " ".repeat(8 - duration.toString().length());
        return duration.toString().concat(space);
    }
}
