package utility;

import entity.Airline;
import entity.Airport;
import entity.Flight;

import java.time.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.LocalDateTime.*;

public class RandomFlightGenerator {
    Random random  = new Random();

    private Airline randomAirlineGenerator(){
        return Airline.values()[random.nextInt(Airline.values().length)];
    }

    private Airport randomAirportGenerator(){
        return Airport.values()[random.nextInt(Airport.values().length)];
    }

    private LocalDateTime randomDateTimeGenerator(){
        long minDay = LocalDate.now().toEpochDay();
        long maxDay = LocalDate.of(2022, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return of(LocalDate.ofEpochDay(randomDay),
                LocalTime.of(random.nextInt(24), random.nextInt(60)));
    }

    private int randomCapacityGenerator(){
        return random.nextInt(100, 300);
    }

    private Duration randomDurationGenerator(){
        return Duration.ofMinutes(random.nextInt(30,300));
    }

    public Flight randomFlightGenerator(){
        Airport to;
        Airline airline = randomAirlineGenerator();
        Airport from = randomAirportGenerator();
            do {
                to = randomAirportGenerator();
            } while (to.equals(from));
        int capacity = randomCapacityGenerator();
        LocalDateTime dateTime = randomDateTimeGenerator();
        Duration duration = randomDurationGenerator();

        return new Flight(from, to, dateTime, duration, airline, random.nextInt(100, 999), capacity);
    }
}
