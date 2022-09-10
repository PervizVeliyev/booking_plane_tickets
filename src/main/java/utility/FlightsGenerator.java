package utility;

import entity.Flight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlightsGenerator {
    public static List<Flight> generator(int count){
        RandomFlightGenerator generator = new RandomFlightGenerator();
        return IntStream.range(0, count)
                .mapToObj(i -> generator.randomFlightGenerator())
                .collect(Collectors.toList());
    }
}
