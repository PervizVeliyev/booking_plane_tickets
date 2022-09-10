package controller;

import entity.Flight;
import logging.Logger;
import service.BookingService;
import service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightController {
    private final FlightService flightService = new FlightService();
    private final BookingService bookingService = new BookingService();

    public List<Flight> getAllFlights(){
        return flightService.getAllFlights();
    }

    public void saveAllFlights(List<Flight> data){
        flightService.saveAll(data);
    }

    public Optional<Flight> get(String airlineCode){
        return flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getAirline().getDesignator().concat(String.valueOf(flight.getCode())).equalsIgnoreCase(airlineCode))
                .findFirst();
    }

    public void decreaseCapacity(String airlineCode, int seats){
        flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getAirline().getDesignator().concat(String.valueOf(flight.getCode())).equalsIgnoreCase(airlineCode))
                .forEach(flight -> {
                    flight.setCapacity(flight.getCapacity() - seats);
                    flightService.save(flight);
                });
        Logger.info(String.format("The capacity decreased for %s flight.", airlineCode));
    }

    public void increaseCapacity(int bookingId){
        Flight flight = bookingService.get(bookingId).get().getFlight();
        int seats = bookingService.get(bookingId).get().getPassengers().size();
        flight.setCapacity(flight.getCapacity() + seats);
        flightService.save(flight);
        Logger.info(String.format("The capacity increased for %s flight.", bookingService.get(bookingId).get().getFlight().getAirline()).concat(
                String.valueOf(bookingService.get(bookingId).get().getFlight().getCode())));
    }

    public void removeExpiredFlights(){
        flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getTime().isBefore(LocalDateTime.now()))
                .forEach(flight -> flightService.remove(flight.getId()));
    }
}
