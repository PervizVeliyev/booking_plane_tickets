package controller;

import entity.Flight;
import logging.Logger;
import service.BookingService;
import service.FlightService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightController {
    private final FlightService flightService = new FlightService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\flights.txt");
    private final BookingService bookingService = new BookingService("C:\\Users\\Perviz Veliyev\\IdeaProjects\\booking_plane_tickets\\src\\database\\bookings.txt");

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
        Flight flight = bookingService.get(bookingId).orElseThrow().getFlight();
        int seats = bookingService.get(bookingId).orElseThrow().getPassengers().size();
        flight.setCapacity(flight.getCapacity() + seats);
        flightService.save(flight);

        Logger.info(String.format("The capacity increased for %s flight.", bookingService.get(bookingId).orElseThrow().getFlight().getAirline()).concat(
                String.valueOf(bookingService.get(bookingId).orElseThrow().getFlight().getCode())));
    }

    public void removeExpiredFlights(){
        flightService.getAllFlights()
                .stream()
                .filter(flight -> flight.getTime().isBefore(LocalDateTime.now()))
                .forEach(flight -> flightService.remove(flight.getId()));
    }
}
